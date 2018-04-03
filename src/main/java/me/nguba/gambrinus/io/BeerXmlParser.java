/**
 *
 */
package me.nguba.gambrinus.io;

import me.nguba.gambrinus.domain.process.DomainFactory;
import me.nguba.gambrinus.domain.process.Schedule;
import me.nguba.gambrinus.domain.process.Step;
import me.nguba.gambrinus.domain.process.Step.Builder;
import me.nguba.gambrinus.domain.process.Temperature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public final class BeerXmlParser {
  
  private static final Logger          LOGGER  = LoggerFactory.getLogger(BeerXmlParser.class);
  
  private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  private final DomainFactory domain = new DomainFactory();
  
  public Schedule parse(final InputStream stream) throws Exception {
    final Document doc = initDocument(stream);

    final XPath xpath = initXpath();

    final String name = parsePofileName(doc, xpath);

    final NodeList stepNodes = parseMashSteps(doc, xpath);

    return domain.makeSchedule(name, parseSteps(stepNodes));
  }

  private static List<Step> parseSteps(final NodeList nl) {
    List<Step> steps = new LinkedList<>();
    for (int i = 0; i < nl.getLength(); i++) {
      final Step step = parseStep(nl, i);

      LOGGER.trace("Mash {}", step);

      steps.add(step);
    }

    return steps;
  }

  private static XPath initXpath() {
    final XPathFactory xPathfactory = XPathFactory.newInstance();
    final XPath xpath = xPathfactory.newXPath();
    return xpath;
  }

  private Document initDocument(final InputStream stream)
      throws ParserConfigurationException, SAXException, IOException {
    final DocumentBuilder builder = factory.newDocumentBuilder();
    final Document doc = builder.parse(stream);
    return doc;
  }

  private static Step parseStep(final NodeList nl, final int i) {
    final Node rast = nl.item(i);
    final NodeList children = rast.getChildNodes();

    Builder builder = Step.builder();

    for (int j = 0; j < children.getLength(); j++) {
      final Node node = children.item(j);
      switch (node.getNodeName()) {
      case "NAME":
        builder.withName(node.getTextContent());
        break;
      case "STEP_TEMP":
        builder.withTarget(Temperature.valueOf(Double.parseDouble(node.getTextContent())));
        break;
      case "STEP_TIME":
        builder.withDuration(Duration.ofMinutes(Long.parseLong(node.getTextContent())));
        break;
      }
    }
    return builder.build();
  }

  static NodeList parseMashSteps(final Document doc, final XPath xpath)
      throws XPathExpressionException {
    final XPathExpression expr = xpath.compile("/RECIPES/RECIPE/MASH/MASH_STEPS/*");
    final NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
    return nl;
  }

  static String parseBoilTime(final Document doc, final XPath xpath)
      throws XPathExpressionException {
    final XPathExpression expr = xpath.compile("/RECIPES/RECIPE/BOIL_TIME/text()");
    final String boilTime = (String) expr.evaluate(doc, XPathConstants.STRING);
    LOGGER.trace("Boil Time: {}", boilTime);
    return boilTime;
  }

  static String parsePofileName(final Document doc, final XPath xpath)
      throws XPathExpressionException {
    final XPathExpression expr = xpath.compile("/RECIPES/RECIPE/NAME/text()");
    final String name = (String) expr.evaluate(doc, XPathConstants.STRING);
    LOGGER.trace("Profile Name: {}", name);
    return name;
  }
}

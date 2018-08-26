/**
 * 
 */
package me.nguba.gambrinus.query.vessel.find;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.equipment.VesselId;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * 
 */
class FindVesselTest
{
  @Test
  void hasVesselId()
  {
    assertThat(FindVessel.of(VesselId.of("HLT")).getId()).isEqualTo(VesselId.of("HLT"));
  }

  @Test
  void equalityContract()
  {
    EqualsVerifier.forClass(FindVessel.class).usingGetClass().verify();
  }

}

/**
 *
 */
package me.nguba.gambrinus.command;

import me.nguba.gambrinus.cqrs.command.Command;
import me.nguba.gambrinus.equipment.VesselId;

/**
 * @author nguba
 *
 */
public interface VesselCommand extends Command
{

  VesselId getId();
}

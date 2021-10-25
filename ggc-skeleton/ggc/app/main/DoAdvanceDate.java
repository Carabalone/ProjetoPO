package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Advance current date.
 */
class DoAdvanceDate extends Command<WarehouseManager> {

  DoAdvanceDate(WarehouseManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    addIntegerField("number", Message.requestDaysToAdvance());

  }

  //TODO exceptions
  @Override
  public final void execute() throws CommandException {
    Integer number = integerField("number");
    _receiver.advanceDate(number);
  }

}

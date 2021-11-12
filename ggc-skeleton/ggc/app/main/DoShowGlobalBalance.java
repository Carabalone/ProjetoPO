package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;

/**
 * Show global balance.
 */
class DoShowGlobalBalance extends Command<WarehouseManager> {

  DoShowGlobalBalance(WarehouseManager receiver) {
    super(Label.SHOW_BALANCE, receiver);
  }

  @Override
  public final void execute() throws CommandException {
    int availableBalance = _receiver.displayGlobalBalance()[0];
    int accountingeBalance = _receiver.displayGlobalBalance()[1];
    _display.popup(Message.currentBalance(availableBalance, accountingeBalance));
  }
  
}

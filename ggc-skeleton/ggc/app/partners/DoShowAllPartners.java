package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;

import java.util.*;
//FIXME import classes

/**
 * Show all partners.
 */
class DoShowAllPartners extends Command<WarehouseManager> {

  DoShowAllPartners(WarehouseManager receiver) {
    super(Label.SHOW_ALL_PARTNERS, receiver);
  }

  //TODO exceptions
  @Override
  public void execute() throws CommandException {
    for (String partner: _receiver.showPartners()) {
      _display.addLine(partner);
    }
    _display.display();
  }

}

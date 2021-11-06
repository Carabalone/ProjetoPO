package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.exception.NoSuchPartnerException;

/**
 * Show batches supplied by partner.
 */
class DoShowBatchesByPartner extends Command<WarehouseManager> {

  DoShowBatchesByPartner(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_SUPPLIED_BY_PARTNER, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
  }

  @Override
  public final void execute() throws CommandException {
    String id = stringField("partnerId");
    try {
      Partner partner = _receiver.getPartner(id);
    } catch NoSuchPartnerExeption e {
      throw new UnknownPartnerKeyException(id);
    }
    for (String b: _receiver.showBatchesPartner()) {
      _display.addLine(batch);
    }
    _display.display();

  }

}

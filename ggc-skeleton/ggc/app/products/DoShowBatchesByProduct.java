package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.core.exception.BadEntryException;
import ggc.app.exception.UnknownProductKeyException;

/**
 * Show all products.
 */
class DoShowBatchesByProduct extends Command<WarehouseManager> {

  DoShowBatchesByProduct(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_BY_PRODUCT, receiver);
    addStringField("productId", Message.requestProductKey());
  }

  @Override
  public final void execute() throws CommandException {
    String id = stringField("productId");

    if (!_receiver.productExists(id))
      throw new UnknownProductKeyException(id);
    
    for (String batch: _receiver.showBatchesProduct(id)) {
      _display.addLine(batch);
    }
    _display.display();
  }

}

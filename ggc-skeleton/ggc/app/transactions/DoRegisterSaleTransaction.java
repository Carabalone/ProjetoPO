package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnavailableProductException;
import ggc.app.exception.UnknownProductKeyException;

/**
 * 
 */
public class DoRegisterSaleTransaction extends Command<WarehouseManager> {

  public DoRegisterSaleTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
    addIntegerField("deadline", Message.requestPaymentDeadline());
    addStringField("productId", Message.requestProductKey());
    addIntegerField("amount", Message.requestAmount()); 
  }

  @Override
  public final void execute() throws CommandException {
    String partner = stringField("partnerId");
    String product = stringField("productId");
    Integer deadline = integerField("deadline");
    Integer amount = integerField("amount");

    if (!_receiver.productExists(product))
      throw new UnknownProductKeyException;

    int quantityAvailable = _receiver.checkProductAvailability(product);
    if (amount > quantityAvailable)
      throw new UnavailableProductException(product, amount, quantityAvailable);

  }

}

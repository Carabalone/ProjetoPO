package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnavailableProductException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.exception.NoSuchPartnerException;
import ggc.core.exception.NoSuchProductException;

/**
 * Registers a sale made to a partner.
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

    try {

      int quantityAvailable = _receiver.checkProductAvailability(product);

      if (amount > quantityAvailable)
        throw new UnavailableProductException(product, amount, quantityAvailable);

      _receiver.addSale(partner, product, deadline, amount);

    } catch(NoSuchPartnerException exPartner){
      throw new UnknownPartnerKeyException(partner);

    } catch (NoSuchProductException exProduct){
      throw new UnknownProductKeyException(product);
    }
  }
}
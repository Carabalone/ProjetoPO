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
 * Register order.
 */
public class DoRegisterBreakdownTransaction extends Command<WarehouseManager> {

  public DoRegisterBreakdownTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_BREAKDOWN_TRANSACTION, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
    addStringField("productId", Message.requestProductKey());
    addIntegerField("amount", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    String partner = stringField("partnerId");
    String product = stringField("productId");
    Integer amount = integerField("amount");

    try {

      int quantityAvailable = _receiver.checkProductAvailability(product);

      if (amount > quantityAvailable)
        throw new UnavailableProductException(product, amount, quantityAvailable);

      _receiver.addBreakdownSale(partner, product, amount);

    } catch(NoSuchPartnerException exPartner){
      throw new UnknownPartnerKeyException(partner);

    } catch (NoSuchProductException exProduct){
      throw new UnknownProductKeyException(product);
    }

  }

}

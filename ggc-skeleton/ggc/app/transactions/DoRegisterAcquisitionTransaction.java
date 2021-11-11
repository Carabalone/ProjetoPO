package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.NoSuchPartnerException;
<<<<<<< Updated upstream
import ggc.core.exception.NoSuchProductException;

=======
import ggc.app.exception.UnknownPartnerKeyException;
>>>>>>> Stashed changes
import java.util.*;

/**
 * Register order.
 */
public class DoRegisterAcquisitionTransaction extends Command<WarehouseManager> {

  public DoRegisterAcquisitionTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_ACQUISITION_TRANSACTION, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
    addStringField("productId", Message.requestProductKey());
    addIntegerField("price", Message.requestPrice());
    addIntegerField("amount", Message.requestAmount());

    /*if (!receiver.productExists(integerField("productId"))){
      System.out.print(Message.requestAddRecipe());
      Scanner sc = new Scanner(System.in);
      if (sc.nextBoolean()) {
        addIntegerField("numberComponents", Message.requestNumberOfComponents());
        addDoubleField("commission", Message.requestAlpha());
      }
    }*/
  }

  @Override
  public final void execute() throws CommandException {
    String partner = stringField("partnerId");
    String product = stringField("productId");
    Integer price = integerField("price");
    Integer amount = integerField("amount");

<<<<<<< Updated upstream
    try{
      if (!_receiver.productExists(product)){
        _receiver.addProduct(product, "SIMPLE");
        _receiver.newBatch(price, amount, product, partner);
      }
      _receiver.addAcquisition(partner, product, price, amount);
    } catch (NoSuchPartnerException ex){
      throw new UnknownPartnerKeyException(ex.getId());
    } catch (NoSuchProductException ex){
      throw new UnknownProductKeyException(ex.getId());

    }
=======
    if (!_receiver.productExists(product))
      _receiver.addProduct(product, "SIMPLE");
    _receiver.newBatch(price, amount, product, partner);

    try {
      _receiver.addAcquisition(partner, product, price, amount);
    } catch (NoSuchPartnerException e) {
      throw new UnknownPartnerKeyException(e.getId());
    }

>>>>>>> Stashed changes
  }

}

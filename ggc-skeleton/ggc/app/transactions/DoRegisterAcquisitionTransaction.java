package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.NoSuchPartnerException;
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

    if (!receiver.productExists(integerField("productId"))){
      addBooleanField("hasRecipe", Message.requestAddRecipe())

      if (booleanField("hasRecipe")) {
        addIntegerField("numberComponents", Message.requestNumberOfComponents());
        addDoubleField("commission", Message.requestAlpha());
        String recipe = "";

        for(int i = 0; i < integerField("numberComponents"); i++){
          addStringField("componentId", Message.requestProductKey());
          addIntegerField("componentQuantity", Message.requestAmount());
          recipe += stringField("componentId") + ":" + integerField("componentQuantity") + "#";
        }

        addStringField("recipe", recipe.substring(0, recipe.length() - 1));
      }
    }
  }

  @Override
  public final void execute() throws CommandException {
    String partner = stringField("partnerId");
    String product = stringField("productId");
    Integer price = integerField("price");
    Integer amount = integerField("amount");

    if (!_receiver.productExists(product))
      if (booleanField("hasRecipe")){
        _receiver.addProduct(product, stringField("recipe"), doubleField("commission"));
      }
      else
        _receiver.addProduct(product);

    try {
      _receiver.newBatch(price, amount, product, partner);
      _receiver.addAcquisition(partner, product, price, amount); 
    } catch (NoSuchPartnerException e) {
      throw new UnknownPartnerKeyException(e.getId());
    }
  }

}

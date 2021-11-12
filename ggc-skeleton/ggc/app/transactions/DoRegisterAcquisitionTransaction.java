package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.*;
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
  }

  @Override
  public final void execute() throws CommandException {
    String partner = stringField("partnerId");
    String product = stringField("productId");
    Integer price = integerField("price");
    Integer amount = integerField("amount");

    if (!_receiver.productExists(product)){
      Form form = new Form("form");
      form.addStringField("request", Message.requestAddRecipe());
      form.parse();
      String answer = form.stringField("request");

      if (answer.compareToIgnoreCase("s") == 0) {
        form.addIntegerField("component", Message.requestNumberOfComponents());
        form.addRealField("alpha", Message.requestAlpha());
        form.parse();
        int numberComponents = form.integerField("component");
        double comission = form.realField("alpha");
        String recipe = "";
        
        for(int i = 0; i < numberComponents; i++){
          form.addStringField("componentId", Message.requestProductKey());
          form.addStringField("quantity", Message.requestAmount());
          form.parse();
          String componentId = form.stringField("componentId");
          String quantity = form.stringField("quantity");
          recipe += componentId + ":" + quantity + "#";
        }
        recipe = recipe.substring(0, recipe.length() - 1);

        _receiver.addProduct(product, recipe, comission);
      }
      else
        _receiver.addProduct(product);
    }

    try {
      _receiver.newBatch(price, amount, product, partner);
      _receiver.addAcquisition(partner, product, price, amount); 
    } catch (NoSuchPartnerException e) {
      throw new UnknownPartnerKeyException(e.getId());
    }
  }

}

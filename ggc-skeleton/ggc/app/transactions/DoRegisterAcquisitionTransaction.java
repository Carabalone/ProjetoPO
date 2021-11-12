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
  }

  @Override
  public final void execute() throws CommandException {
    String partner = stringField("partnerId");
    String product = stringField("productId");
    Integer price = integerField("price");
    Integer amount = integerField("amount");
    Scanner sc = new Scanner(System.in);

    if (!_receiver.productExists(product)){
      System.out.print(Message.requestAddRecipe());
      boolean hasRecipe = sc.nextBoolean();

      if (hasRecipe) {
        System.out.print(Message.requestNumberOfComponents());
        int numberComponents = sc.nextInt();
        System.out.print(Message.requestAlpha());
        double comission = sc.nextDouble();
        String recipe = "";

        for(int i = 0; i < numberComponents; i++){
          System.out.print(Message.requestProductKey());
          String componentId = sc.nextLine();
          System.out.print(Message.requestAmount());
          String quantity = sc.nextLine();
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

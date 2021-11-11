package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.NoSuchPartnerException;
import ggc.core.exception.NoSuchProductException;

/**
 * Toggle product-related notifications.
 */
class DoToggleProductNotifications extends Command<WarehouseManager> {

  DoToggleProductNotifications(WarehouseManager receiver) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
    addStringField("productId", Message.requestProductKey());
  }

  @Override
  public void execute() throws CommandException {
    String partnerId = stringField("partnerId");
    String productId = stringField("productId");

    try{
      _receiver.toggleNotifications(productId, partnerId);
    } catch (NoSuchProductException ex){
      throw new UnknownProductKeyException(ex.getId());
    } catch (NoSuchPartnerException ex){
      throw new UnknownPartnerKeyException(ex.getId());
    }
  }

}

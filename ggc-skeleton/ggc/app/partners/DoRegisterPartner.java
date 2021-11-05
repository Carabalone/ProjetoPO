package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.DuplicatePartnerKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.DuplicatePartnerIdException;

/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {

  DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);
    addStringField("id", Message.requestPartnerKey());
    addStringField("name", Message.requestPartnerName());
    addStringField("adress", Message.requestPartnerAddress()); 
  }

  @Override
  public void execute() throws CommandException {
    String id = stringField("id");
    String name = stringField("name");
    String adress = stringField("adress");
    try{
      _receiver.addPartner(name, id, adress);
    } catch (DuplicatePartnerIdException ex){
      throw new DuplicatePartnerKeyException(ex.getId());
    }
  }

}

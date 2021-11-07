package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnknownTransactionKeyException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<WarehouseManager> {

  public DoShowTransaction(WarehouseManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    addIntegerField("id", Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws CommandException {
    Integer id = integerField("id");
    String transaction;
    try{
      transaction = _receiver.getTransaction(id);
    } catch (IndexOutOfBoundsException e){
      throw new UnknownTransactionKeyException(id);
    }
    _display.display(transaction);
  }

}

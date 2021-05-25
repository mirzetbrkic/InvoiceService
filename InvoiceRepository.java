import java.util.ArrayList;

public interface InvoiceRepository {
    ArrayList<Invoice> loadInvoices();

    void storeInvoice(Invoice invoice);

    Invoice getInvoiceById(int id);
}

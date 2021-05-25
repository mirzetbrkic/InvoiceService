import java.util.ArrayList;
import java.util.stream.Collectors;

public class InvoiceService {
    InvoiceRepository invoiceRepository;

    void add_item_to_invoice(int invoiceId, Item item) {
        ArrayList<Invoice> allInvoices = invoiceRepository.loadInvoices();

        Invoice invoice = null;
        for (int i = 0; i < allInvoices.size(); i++)
            if (allInvoices.get(i).id == invoiceId)
                invoice = allInvoices.get(i);

        invoice.items.add(item);

        invoiceRepository.storeInvoice(invoice);
    }

    void calculateTotalAmount(int invoiceId) {
        ArrayList<Invoice> allInvoices = invoiceRepository.loadInvoices();

        Invoice invoice = null;
        for (int i = 0; i < allInvoices.size(); i++)
            if (allInvoices.get(i).id == invoiceId)
                invoice = allInvoices.get(i);

        ArrayList<Item> items = invoice.items;
        double totalAmount = items.stream().map(i -> i.price).collect(Collectors.summingDouble(Double::doubleValue));
    }

    void split_the_invoice(int invoiceId, int rate) {
        Invoice invoice = invoiceRepository.getInvoiceById(invoiceId);

        float totalValue = invoice.total / rate;

        invoice.total = totalValue;

        invoiceRepository.storeInvoice(invoice);
    }
}

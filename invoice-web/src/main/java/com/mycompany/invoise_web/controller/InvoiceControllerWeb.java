package com.mycompany.invoise_web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.invoise.controller.InvoiceControllerInterface;
import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.service.InvoiceServiceInterface;

@Controller
@RequestMapping("/invoice")
public class InvoiceControllerWeb implements InvoiceControllerInterface{
	
	public InvoiceControllerWeb(InvoiceServiceInterface invoiceService) {
	        this.invoiceService = invoiceService;
	    }
	@Autowired
	  private final InvoiceServiceInterface invoiceService;
	  
	public InvoiceServiceInterface getInvoiceService() {
		return invoiceService;
	}

	@PostMapping
	public String createInvoice( Invoice invoice) {
		
        invoiceService.createInvoice(invoice);
        return "invoice-created";
        
	}

	@GetMapping("/home")
	
	public String displayHome(Model model) {
		model.addAttribute("invoices",invoiceService.getInvoiceList());
		return "invoice-home";
	}
	@GetMapping("/{id}")
	public String displayInvoice(@PathVariable("id") String number,Model model) {
		model.addAttribute("invoice",invoiceService.getInvoiceByNumber(number));
		return "invoice-details";
	}
	
	@GetMapping("/create-form")
	public String displayInvoiceCreateForm (@ModelAttribute Invoice invoice) {
		return "invoice-create-form";
	}

	@Override
	public void setInvoiceService(InvoiceServiceInterface invoiceServiceInterface) {
		// TODO Auto-generated method stub
		
	}


}

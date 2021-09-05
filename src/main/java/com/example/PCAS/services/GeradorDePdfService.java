package com.example.PCAS.services;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.entities.Hospital;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class GeradorDePdfService {
	
	@Autowired
	private GeradorDeRelatorio geraDeRelatorioService;

	public void gerarRelatorio(HttpServletResponse response) throws IOException {
		relatorioHospComOcupacaoMenorQue90(response);
		relatorioHospComOcupacaoMaiorQue90(response);
	}

	public void relatorioHospComOcupacaoMaiorQue90(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Hospitais com ocupação maior que 90%", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        document.add(paragraph);
        
        //tabela
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 4.0f, 3.0f, 2.5f});
        table.setSpacingBefore(10);
        
        
        //escrevendo header da tabela
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("CNPJ", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Nome", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Endereço", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Localização", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Ocupação %", font));
        table.addCell(cell);
        
        //escrevendo dados na tabela 
        for (Hospital h: geraDeRelatorioService.hospComOcupacaoMaiorQue90()) {
        	table.addCell(h.getCnpj());
            table.addCell(h.getNome());
            table.addCell(h.getEndereco().toString());
            table.addCell(h.getLocalizacao().toString());
            table.addCell(String.valueOf(h.getPercOcupacao()) + "%");
        }
        
        document.add(table);
        
        document.close();
	}
	
	public void relatorioHospComOcupacaoMenorQue90(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Hospitais com ocupação menor que 90%", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        document.add(paragraph);
        
        //tabela
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 4.0f, 3.0f, 2.5f});
        table.setSpacingBefore(10);
        
        
        //escrevendo header da tabela
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("CNPJ", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Nome", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Endereço", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Localização", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Ocupação %", font));
        table.addCell(cell);
        
        //escrevendo dados na tabela 
        for (Hospital h: geraDeRelatorioService.hospComOcupacaoMenorQue90()) {
        	table.addCell(h.getCnpj());
            table.addCell(h.getNome());
            table.addCell(h.getEndereco().toString());
            table.addCell(h.getLocalizacao().toString());
            table.addCell(String.valueOf(h.getPercOcupacao()) + "%");
        }
        
        document.add(table);
        
        document.close();
	}
	
}

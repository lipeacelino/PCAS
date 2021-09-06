package com.example.PCAS.services;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.DTO.RecursoRelatorioDTO;
import com.example.PCAS.entities.HistoricoTransacao;
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
	
	/*
	 * Eu optei por gerar um único relatório com todos os requisitos que
	 * foi pedido na descrição do problema
	 * 
	 * Pela descrição do problema os hospitais com ocupção igual a 90% não estavam 
	 * inclusos em nenhum relatório, portanto tomei a liberdade de uma tabela 
	 * de hospitais com ocupação igual a 90%
	 * 
	 */

	@Autowired
	private GeradorDeRelatorio geraDeRelatorioService;

	public void gerarRelatorio(HttpServletResponse response) throws IOException {
		try {
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, response.getOutputStream());

			document.open();
			
			relatorioHospitalComOcupacao90(response, document);
			relatorioHospComOcupacaoMenorQue90(response, document);
			relatorioHospComOcupacaoMaiorQue90(response, document);
			relatorioMediaDeRecursos(response, document);
			relatorioHospComOcupacaoMenorQue90PorMaisTempo(response, document);
			relatorioHospComOcupacaoMaiorQue90PorMaisTempo(response, document);
			relatorioHistoricoDeTransacoes(response, document);

			document.close();
		} catch (DocumentException e) {
			throw new DocumentException("Erro ao gerar relatório");
		} catch (IOException e) {
			throw new IOException("Erro ao gerar relatório");
		}

	}

	public void relatorioHospComOcupacaoMaiorQue90(HttpServletResponse response, Document document)
			throws DocumentException, IOException {

		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);

		Paragraph paragraph = new Paragraph("Hospitais com ocupação maior que 90%", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.setSpacingAfter(10);
		paragraph.setSpacingBefore(35);
		document.add(paragraph);

		// tabela
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.0f, 4.0f, 1.0f, 2.0f });

		List<Hospital> htList = geraDeRelatorioService.relaHospComOcupacaoMaiorQue90();

		if (htList.isEmpty()) {
			Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
			fontParagraph.setSize(12);
			Paragraph paragraph2 = new Paragraph("Ainda não há dados para serem exibidos!", fontParagraph);
			paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(paragraph2);
		} else {
			// escrevendo header da tabela
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

			cell.setPhrase(new Phrase("Ocup. %", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Recursos", font));
			table.addCell(cell);

			// escrevendo dados na tabela
			for (Hospital h : geraDeRelatorioService.relaHospComOcupacaoMaiorQue90()) {
				table.addCell(h.getCnpj());
				table.addCell(h.getNome());
				table.addCell(h.getEndereco().toString());
				table.addCell(String.valueOf(h.getPercOcupacao()) + "%");
				table.addCell(h.getRecurso().toString());
			}
		}

		document.add(table);

	}

	public void relatorioHistoricoDeTransacoes(HttpServletResponse response, Document document)
			throws DocumentException, IOException {

		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);

		Paragraph paragraph = new Paragraph("Histórico de Negociação", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.setSpacingAfter(10);
		paragraph.setSpacingBefore(35);
		document.add(paragraph);

		// tabela
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.0f, 1.0f, 2.0f, 1.0f, 1.0f });

		List<HistoricoTransacao> htList = geraDeRelatorioService.historicoTransacoes();

		if (htList.isEmpty()) {
			Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
			fontParagraph.setSize(12);
			Paragraph paragraph2 = new Paragraph("Ainda não há dados para serem exibidos!", fontParagraph);
			paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(paragraph2);
		} else {
			// escrevendo header da tabela
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(Color.DARK_GRAY);
			cell.setPadding(5);

			Font font = FontFactory.getFont(FontFactory.HELVETICA);
			font.setColor(Color.WHITE);

			cell.setPhrase(new Phrase("Nome do Hospital", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Rec. Usados na Troca", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Nome do Hospital", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Rec. Usados na Troca", font));
			table.addCell(cell);

			// escrevendo dados na tabela
			for (HistoricoTransacao ht : geraDeRelatorioService.historicoTransacoes()) {
				table.addCell(ht.getH1().getNome());
				table.addCell(ht.getRecurso1().toString());
				table.addCell(ht.getH1().getNome() + ", trocou recursos com " + ht.getH2().getNome());
				table.addCell(ht.getH2().getNome());
				table.addCell(ht.getRecurso2().toString());
			}
		}

		document.add(table);

	}

	public void relatorioMediaDeRecursos(HttpServletResponse response, Document document)
			throws DocumentException, IOException {

		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);

		Paragraph paragraph = new Paragraph("Média de recursos", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.setSpacingAfter(10);
		paragraph.setSpacingBefore(35);
		document.add(paragraph);

		// tabela
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 3.0f, 3.0f, 3.0f, 3.0f, 3.0f });

		// escrevendo header da tabela
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.DARK_GRAY);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Médico", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Enfermeiro", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Respirador", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Tomógrafo", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Ambulância", font));
		table.addCell(cell);

		// escrevendo dados na tabela

		RecursoRelatorioDTO rr = geraDeRelatorioService.relMediaDeRecursos();

		table.addCell(String.valueOf(rr.getMedico()));
		table.addCell(String.valueOf(rr.getEnfermeiro()));
		table.addCell(String.valueOf(rr.getRespirador()));
		table.addCell(String.valueOf(rr.getTomografo()));
		table.addCell(String.valueOf(rr.getAmbulancia()));

		document.add(table);

	}

	public void relatorioHospComOcupacaoMaiorQue90PorMaisTempo(HttpServletResponse response, Document document)
			throws DocumentException, IOException {
		
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);

		Paragraph paragraph = new Paragraph("Hospital com ocupação maior que 90% por mais tempo", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.setSpacingAfter(10);
		paragraph.setSpacingBefore(35);
		document.add(paragraph);

		// tabela
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.0f, 4.0f, 1.0f, 2.0f });

		// escrevendo header da tabela
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

		cell.setPhrase(new Phrase("Ocup. %", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Recursos", font));
		table.addCell(cell);

		// escrevendo dados na tabela
		Hospital h = geraDeRelatorioService.relaHospitalComOcupacaoMaiorQue90PorMaisTempo();
		table.addCell(h.getCnpj());
		table.addCell(h.getNome());
		table.addCell(h.getEndereco().toString());
		table.addCell(String.valueOf(h.getPercOcupacao()) + "%");
		table.addCell(h.getRecurso().toString());
		
		document.add(table);

	}

	public void relatorioHospComOcupacaoMenorQue90PorMaisTempo(HttpServletResponse response, Document document)
			throws DocumentException, IOException {

		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);

		Paragraph paragraph = new Paragraph("Hospital com ocupação menor que 90% por mais tempo", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.setSpacingAfter(10);
		paragraph.setSpacingBefore(35);
		document.add(paragraph);

		// tabela
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.0f, 4.0f, 1.0f, 2.0f });

		// escrevendo header da tabela
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

		cell.setPhrase(new Phrase("Ocup. %", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Recursos", font));
		table.addCell(cell);

		// escrevendo dados na tabela
		Hospital h = geraDeRelatorioService.relaHospitalComOcupacaoMenorQue90PorMaisTempo();
		table.addCell(h.getCnpj());
		table.addCell(h.getNome());
		table.addCell(h.getEndereco().toString());
		table.addCell(String.valueOf(h.getPercOcupacao())+"%");
		table.addCell(h.getRecurso().toString());

		document.add(table);

	}

	public void relatorioHospComOcupacaoMenorQue90(HttpServletResponse response, Document document)
			throws DocumentException, IOException {
		
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);

		Paragraph paragraph = new Paragraph("Hospitais com ocupação menor que 90%", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.setSpacingAfter(10);
		paragraph.setSpacingBefore(35);
		document.add(paragraph);

		// tabela
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.0f, 4.0f, 1.0f, 2.0f });

		List<Hospital> htList = geraDeRelatorioService.relaHospComOcupacaoMenorQue90();

		if (htList.isEmpty()) {
			Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
			fontParagraph.setSize(12);
			Paragraph paragraph2 = new Paragraph("Ainda não há dados para serem exibidos!", fontParagraph);
			paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(paragraph2);
		} else {
			// escrevendo header da tabela
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

			cell.setPhrase(new Phrase("Ocup. %", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Recursos", font));
			table.addCell(cell);

			// escrevendo dados na tabela
			for (Hospital h : geraDeRelatorioService.relaHospComOcupacaoMenorQue90()) {
				table.addCell(h.getCnpj());
				table.addCell(h.getNome());
				table.addCell(h.getEndereco().toString());
				table.addCell(String.valueOf(h.getPercOcupacao()) + "%");
				table.addCell(h.getRecurso().toString());
			}
		}
		
		document.add(table);
	
	}

	public void relatorioHospitalComOcupacao90(HttpServletResponse response, Document document)
			throws DocumentException, IOException {

		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);

		Paragraph paragraph = new Paragraph("Hospitais com ocupação igual a 90%", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.setSpacingAfter(10);
		paragraph.setSpacingBefore(35);
		document.add(paragraph);

		// tabela
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.0f, 4.0f, 1.0f, 2.0f });

		List<Hospital> htList = geraDeRelatorioService.relaHospComOcupacaoIgual90();

		if (htList.isEmpty()) {
			Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
			fontParagraph.setSize(12);
			Paragraph paragraph2 = new Paragraph("Ainda não há dados para serem exibidos!", fontParagraph);
			paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(paragraph2);
		} else {
			// escrevendo header da tabela
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

			cell.setPhrase(new Phrase("Ocup. %", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Recursos", font));
			table.addCell(cell);

			// escrevendo dados na tabela
			for (Hospital h : geraDeRelatorioService.relaHospComOcupacaoIgual90()) {
				table.addCell(h.getCnpj());
				table.addCell(h.getNome());
				table.addCell(h.getEndereco().toString());
				table.addCell(String.valueOf(h.getPercOcupacao()) + "%");
				table.addCell(h.getRecurso().toString());
			}
		}

		document.add(table);

	}
	
}

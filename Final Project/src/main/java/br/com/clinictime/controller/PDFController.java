package br.com.clinictime.controller;

import br.com.clinictime.model.Consulta;


import com.itextpdf.text.*;
import org.springframework.stereotype.Controller;


import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;


@Controller
public class PDFController {

    public byte[] gerarPDFConsulta(Consulta consulta) throws DocumentException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, output);
            document.open();

            // centralizar e espaçamento
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.BOLD, BaseColor.BLACK);
            Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 23, Font.NORMAL, BaseColor.BLACK);



            document.add(createCenteredParagraphWithSpacing("Dr: " + consulta.getDoutor().getName(), titleFont, 10, 15));
            document.add(createCenteredParagraphWithSpacing("Paciente: " + consulta.getPaciente().getName(), contentFont, 15, 20));
            document.add(createCenteredParagraphWithSpacing("Receita: " + consulta.getReceita(), contentFont, 20, 260));
            document.add(createCenteredParagraphWithSpacing("CRM: " + consulta.getDoutor().getCrm(), contentFont, 260, 10));

            document.close();
        } catch (DocumentException e) {

            e.printStackTrace();
            throw e;
        }

        return output.toByteArray();
    }

    private Paragraph createCenteredParagraphWithSpacing(String content, Font font, float spacingBefore, float spacingAfter) {
        Paragraph paragraph = new Paragraph(content, font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingBefore(spacingBefore);
        paragraph.setSpacingAfter(spacingAfter);
        return paragraph;
    }
}


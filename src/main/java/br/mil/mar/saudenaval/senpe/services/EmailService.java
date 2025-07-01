package br.mil.mar.saudenaval.senpe.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;



    public void sendInstructionsByMail(String email, String nome ,String userId) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom("reginaldo.elias@marinha.mil.br");
       // helper.setTo(destinatario);
        helper.setTo(email);
        helper.setSubject("Recuperação de senha do Sistema de Encaminhamento de Pedido de Exames");
        String body = "<html><body>" +
                "<h1>Instruções para Recuperação de Senha</h1>" +
                "<p>Olá " + nome + ", </p>" +
                "<p>Recebemos uma solicitação para redefinir a sua senha. Se você não fez essa solicitação, por favor ignore este e-mail.</p>" +
                "<p>Para redefinir a sua senha, clique no link abaixo:</p>" +
                "<p><a href='http://127.0.0.1:3000/reset-password/" + userId + "'>Redefinir Senha</a></p>" +
                "<p>Se o link acima não funcionar, copie e cole a seguinte URL no seu navegador:</p>" +
                "<p>http://127.0.0.1:3000/reset-password/" + userId + "</p>" +
                "<p>Atenciosamente,<br>Saúde Naval</p>" +
                "</body></html>";
        helper.setText(body,true);


        javaMailSender.send(mimeMessage);

    }


    public void sendProtocolByEmail(String email, String nome, String protocolo) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom("reginaldo.elias@marinha.mil.br");

        helper.setTo(email);
        helper.setSubject("Confirmação de Solicitação de Marcação de Exame");
       /* String body = "<html><body>"+
                "<p>Prezado(a) " + nome + ", </p>" +
                "<p>Estamos confirmando o recebimento da sua solicitação de marcação de exame.</p> <br />" +
                "<p>Protocolo: "+ protocolo +" </p> <br />" +
                "<p>Agradecemos por utilizar nossos serviços. Em breve, entraremos em contato para confirmar a data e horário do seu exame. Se tiver qualquer dúvida ou precisar de mais informações, por favor, não hesite em nos contatar.</p> " +
                "<br /> <p>Atenciosamente,</p>"+
                "</body></html>";*/


        String body = "<head>\t<style type='text/css'>/* ------------------------------------- \n" +
                "\t\tGLOBAL \n" +
                "------------------------------------- */\n" +
                "* { \n" +
                "\tmargin:0;\n" +
                "\tpadding:0;\n" +
                "}\n" +
                "* { font-family: 'Open Sans', Helvetica, Arial, sans-serif; }\n" +
                "\n" +
                "img { \n" +
                "\tmax-width: 100%; \n" +
                "}\n" +
                ".collapse {\n" +
                "\tmargin:0;\n" +
                "\tpadding:0;\n" +
                "}\n" +
                "body {\n" +
                "\t-webkit-font-smoothing:antialiased; \n" +
                "\t-webkit-text-size-adjust:none; \n" +
                "\twidth: 100%!important; \n" +
                "\theight: 100%;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tELEMENTS \n" +
                "------------------------------------- */\n" +
                "a { color: #2BA6CB;}\n" +
                "\n" +
                ".btn {\n" +
                "\ttext-decoration:none;\n" +
                "\tcolor: #50514f;\n" +
                "\tbackground-color: #f1b90f;;\n" +
                "\tpadding:10px 16px;\n" +
                "\tfont-weight:bold;\n" +
                "\tmargin-right:10px;\n" +
                "\ttext-align:center;\n" +
                "\tcursor:pointer;\n" +
                "\tdisplay: inline-block;\n" +
                "}\n" +
                "\n" +
                "p.callout {\n" +
                "\tpadding:15px;\n" +
                "\tbackground-color:#ECF8FF;\n" +
                "\tmargin-bottom: 15px;\n" +
                "}\n" +
                ".callout a {\n" +
                "\tfont-weight:bold;\n" +
                "\tcolor: #2BA6CB;\n" +
                "}\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tHEADER \n" +
                "------------------------------------- */\n" +
                "table.head-wrap { width: 100%;}\n" +
                "\n" +
                ".header.container table td.logo { padding: 15px; }\n" +
                ".header.container table td.label { padding: 15px; padding-left:0px;}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tBODY \n" +
                "------------------------------------- */\n" +
                "table.body-wrap { width: 100%;}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tFOOTER \n" +
                "------------------------------------- */\n" +
                "table.footer-wrap { width: 100%;\tclear:both!important;\n" +
                "}\n" +
                ".footer-wrap .container td.content  p { border-top: 1px solid rgb(215,215,215); padding-top:15px;}\n" +
                ".footer-wrap .container td.content p {\n" +
                "\tfont-size:10px;\n" +
                "\tfont-weight: bold;\n" +
                "\t\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tTYPOGRAPHY \n" +
                "------------------------------------- */\n" +
                "h1,h2,h3,h4,h5,h6 {\n" +
                "font-family: 'Open Sans', Helvetica, Arial, sans-serif; line-height: 1.1; margin-bottom:15px; color:#000;\n" +
                "}\n" +
                "h1 small, h2 small, h3 small, h4 small, h5 small, h6 small { font-size: 60%; color: #6f6f6f; line-height: 0; text-transform: none; }\n" +
                "\n" +
                "h1 { font-weight:200; font-size: 44px;}\n" +
                "h2 { font-weight:200; font-size: 37px;}\n" +
                "h3 { font-weight:500; font-size: 27px;}\n" +
                "h4 { font-weight:500; font-size: 23px;}\n" +
                "h5 { font-weight:900; font-size: 17px;}\n" +
                "h6 { font-weight:900; font-size: 14px; text-transform: uppercase; color:#fff;}\n" +
                "\n" +
                ".collapse { margin:0!important;}\n" +
                "\n" +
                "p, ul { \n" +
                "\tmargin-bottom: 10px; \n" +
                "\tfont-weight: normal; \n" +
                "\tfont-size:14px; \n" +
                "\tline-height:1.6;\n" +
                "    text-align: justify;\n" +
                "}\n" +
                "p.lead { font-size:17px; }\n" +
                "p.last { margin-bottom:0px;}\n" +
                "\n" +
                "ul li {\n" +
                "\tmargin-left:5px;\n" +
                "\tlist-style-position: inside;\n" +
                "}\n" +
                "\n" +
                "/* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n" +
                ".container {\n" +
                "\tdisplay:block!important;\n" +
                "\tmax-width:600px!important;\n" +
                "\tmargin:0 auto!important; /* makes it centered */\n" +
                "\tclear:both!important;\n" +
                "}\n" +
                "\n" +
                "/* This should also be a block element, so that it will fill 100% of the .container */\n" +
                ".content {\n" +
                "\tpadding:15px;\n" +
                "\tmax-width:600px;\n" +
                "\tmargin:0 auto;\n" +
                "\tdisplay:block; \n" +
                "}\n" +
                "\n" +
                "/* Let's make sure tables in the content area are 100% wide */\n" +
                ".content table { width: 100%; }\n" +
                "\n" +
                "\n" +
                "/* Odds and ends */\n" +
                ".column {\n" +
                "\twidth: 300px;\n" +
                "\tfloat:left;\n" +
                "}\n" +
                ".column tr td { padding: 15px; }\n" +
                ".column-wrap { \n" +
                "\tpadding:0!important; \n" +
                "\tmargin:0 auto; \n" +
                "\tmax-width:600px!important;\n" +
                "}\n" +
                ".column table { width:100%;}\n" +
                ".social .column {\n" +
                "\twidth: 280px;\n" +
                "\tmin-width: 279px;\n" +
                "\tfloat:left;\n" +
                "}\n" +
                "\n" +
                "/* Be sure to place a .clear element after each set of columns, just to be safe */\n" +
                ".clear { display: block; clear: both; }\n" +
                "\n" +
                "@media only screen and (max-width: 600px) {\n" +
                "\t\n" +
                "\ta[class='btn'] { display:block!important; margin-bottom:10px!important; background-image:none!important; margin-right:0!important;}\n" +
                "\n" +
                "\tdiv[class='column'] { width: auto!important; float:none!important;}\n" +
                "\t\n" +
                "\ttable.social div[class='column'] {\n" +
                "\t\twidth:auto!important;\n" +
                "\t}\n" +
                "\n" +
                "}\n" +
                "\t</style>" +
                "" +
                "</head>" +
                "<body bgcolor='#FFFFFF' leftmargin='0' marginheight='0' marginwidth='0' topmargin='0' style=''>\n" +
                "<table bgcolor='#2E7CBD' class='head-wrap'>\n" +
                "<tbody class=''>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td align='' class='header container'>\n" +
                "<div class='content'>\n" +
                "<table bgcolor='#2E7CBD'>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td><a href='https://www.marinha.mil.br/saudenaval/'><img alt='' height='auto' src='https://www.marinha.mil.br/saudenaval/sites/www.marinha.mil.br.saudenaval/files/logo-especialidades.png' width='180' /></a></td>\n" +
                "<td align='right'>\n" +
                "<h6 class='collapse'>"+"Confirmação de Solicitação de Marcação de Exame"+"</h6>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td>&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table bgcolor='' class='body-wrap'>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td align='' bgcolor='#FFFFFF' class='container'>\n" +
                "<div class='content'>\n" +
                "<table>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<h2><span style='font-size: 14pt;'>Ol&aacute;, <strong>"+ nome +"</strong>!</span></h2>\n" +
                "&nbsp;\n" +
                "<p>Estamos confirmando o recebimento da sua solicitação de marcação de exame.</p>\n" +
                "<p>Protocolo: <strong>"+ protocolo +"</strong> </p> <br />" +
                "<p>Agradecemos por utilizar nossos serviços. Em breve, entraremos em contato para confirmar a data e horário do seu exame. Se tiver qualquer dúvida ou precisar de mais informações, por favor, não hesite em nos contatar.</p> " +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td>&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table bgcolor='#2E7CBD' class='head-wrap'>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td align='' class='header container'>\n" +
                "<div class='content' style='text-align: center;'>\n" +
                "<p style='color: #fff;'>Este e-mail foi enviado para "+ email +".<br />Caso queira, voc&ecirc; poder&aacute; entrar em contato a qualquer momento.<br />Fale conosco atrav&eacute;s do saudenaval@marinha.mil.br.</p>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td>&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</body>";

        helper.setText(body,true);
        javaMailSender.send(mimeMessage);
    }

    public void changeStatusByMail(String email, String nome, String protocolo) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom("reginaldo.elias@marinha.mil.br");

        helper.setTo(email);
        helper.setSubject("Informação da situação do Pedido de Exame");


        String body = "<head>\t<style type='text/css'>/* ------------------------------------- \n" +
                "\t\tGLOBAL \n" +
                "------------------------------------- */\n" +
                "* { \n" +
                "\tmargin:0;\n" +
                "\tpadding:0;\n" +
                "}\n" +
                "* { font-family: 'Open Sans', Helvetica, Arial, sans-serif; }\n" +
                "\n" +
                "img { \n" +
                "\tmax-width: 100%; \n" +
                "}\n" +
                ".collapse {\n" +
                "\tmargin:0;\n" +
                "\tpadding:0;\n" +
                "}\n" +
                "body {\n" +
                "\t-webkit-font-smoothing:antialiased; \n" +
                "\t-webkit-text-size-adjust:none; \n" +
                "\twidth: 100%!important; \n" +
                "\theight: 100%;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tELEMENTS \n" +
                "------------------------------------- */\n" +
                "a { color: #2BA6CB;}\n" +
                "\n" +
                ".btn {\n" +
                "\ttext-decoration:none;\n" +
                "\tcolor: #50514f;\n" +
                "\tbackground-color: #f1b90f;;\n" +
                "\tpadding:10px 16px;\n" +
                "\tfont-weight:bold;\n" +
                "\tmargin-right:10px;\n" +
                "\ttext-align:center;\n" +
                "\tcursor:pointer;\n" +
                "\tdisplay: inline-block;\n" +
                "}\n" +
                "\n" +
                "p.callout {\n" +
                "\tpadding:15px;\n" +
                "\tbackground-color:#ECF8FF;\n" +
                "\tmargin-bottom: 15px;\n" +
                "}\n" +
                ".callout a {\n" +
                "\tfont-weight:bold;\n" +
                "\tcolor: #2BA6CB;\n" +
                "}\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tHEADER \n" +
                "------------------------------------- */\n" +
                "table.head-wrap { width: 100%;}\n" +
                "\n" +
                ".header.container table td.logo { padding: 15px; }\n" +
                ".header.container table td.label { padding: 15px; padding-left:0px;}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tBODY \n" +
                "------------------------------------- */\n" +
                "table.body-wrap { width: 100%;}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tFOOTER \n" +
                "------------------------------------- */\n" +
                "table.footer-wrap { width: 100%;\tclear:both!important;\n" +
                "}\n" +
                ".footer-wrap .container td.content  p { border-top: 1px solid rgb(215,215,215); padding-top:15px;}\n" +
                ".footer-wrap .container td.content p {\n" +
                "\tfont-size:10px;\n" +
                "\tfont-weight: bold;\n" +
                "\t\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tTYPOGRAPHY \n" +
                "------------------------------------- */\n" +
                "h1,h2,h3,h4,h5,h6 {\n" +
                "font-family: 'Open Sans', Helvetica, Arial, sans-serif; line-height: 1.1; margin-bottom:15px; color:#000;\n" +
                "}\n" +
                "h1 small, h2 small, h3 small, h4 small, h5 small, h6 small { font-size: 60%; color: #6f6f6f; line-height: 0; text-transform: none; }\n" +
                "\n" +
                "h1 { font-weight:200; font-size: 44px;}\n" +
                "h2 { font-weight:200; font-size: 37px;}\n" +
                "h3 { font-weight:500; font-size: 27px;}\n" +
                "h4 { font-weight:500; font-size: 23px;}\n" +
                "h5 { font-weight:900; font-size: 17px;}\n" +
                "h6 { font-weight:900; font-size: 14px; text-transform: uppercase; color:#fff;}\n" +
                "\n" +
                ".collapse { margin:0!important;}\n" +
                "\n" +
                "p, ul { \n" +
                "\tmargin-bottom: 10px; \n" +
                "\tfont-weight: normal; \n" +
                "\tfont-size:14px; \n" +
                "\tline-height:1.6;\n" +
                "    text-align: justify;\n" +
                "}\n" +
                "p.lead { font-size:17px; }\n" +
                "p.last { margin-bottom:0px;}\n" +
                "\n" +
                "ul li {\n" +
                "\tmargin-left:5px;\n" +
                "\tlist-style-position: inside;\n" +
                "}\n" +
                "\n" +
                "/* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n" +
                ".container {\n" +
                "\tdisplay:block!important;\n" +
                "\tmax-width:600px!important;\n" +
                "\tmargin:0 auto!important; /* makes it centered */\n" +
                "\tclear:both!important;\n" +
                "}\n" +
                "\n" +
                "/* This should also be a block element, so that it will fill 100% of the .container */\n" +
                ".content {\n" +
                "\tpadding:15px;\n" +
                "\tmax-width:600px;\n" +
                "\tmargin:0 auto;\n" +
                "\tdisplay:block; \n" +
                "}\n" +
                "\n" +
                "/* Let's make sure tables in the content area are 100% wide */\n" +
                ".content table { width: 100%; }\n" +
                "\n" +
                "\n" +
                "/* Odds and ends */\n" +
                ".column {\n" +
                "\twidth: 300px;\n" +
                "\tfloat:left;\n" +
                "}\n" +
                ".column tr td { padding: 15px; }\n" +
                ".column-wrap { \n" +
                "\tpadding:0!important; \n" +
                "\tmargin:0 auto; \n" +
                "\tmax-width:600px!important;\n" +
                "}\n" +
                ".column table { width:100%;}\n" +
                ".social .column {\n" +
                "\twidth: 280px;\n" +
                "\tmin-width: 279px;\n" +
                "\tfloat:left;\n" +
                "}\n" +
                "\n" +
                "/* Be sure to place a .clear element after each set of columns, just to be safe */\n" +
                ".clear { display: block; clear: both; }\n" +
                "\n" +
                "@media only screen and (max-width: 600px) {\n" +
                "\t\n" +
                "\ta[class='btn'] { display:block!important; margin-bottom:10px!important; background-image:none!important; margin-right:0!important;}\n" +
                "\n" +
                "\tdiv[class='column'] { width: auto!important; float:none!important;}\n" +
                "\t\n" +
                "\ttable.social div[class='column'] {\n" +
                "\t\twidth:auto!important;\n" +
                "\t}\n" +
                "\n" +
                "}\n" +
                "\t</style>" +
                "" +
                "</head>" +
                "<body bgcolor='#FFFFFF' leftmargin='0' marginheight='0' marginwidth='0' topmargin='0' style=''>\n" +
                "<table bgcolor='#2E7CBD' class='head-wrap'>\n" +
                "<tbody class=''>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td align='' class='header container'>\n" +
                "<div class='content'>\n" +
                "<table bgcolor='#2E7CBD'>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td><a href='https://www.marinha.mil.br/saudenaval/'><img alt='' height='auto' src='https://www.marinha.mil.br/saudenaval/sites/www.marinha.mil.br.saudenaval/files/logo-especialidades.png' width='180' /></a></td>\n" +
                "<td align='right'>\n" +
                "<h6 class='collapse'>"+"Confirmação de Solicitação de Marcação de Exame"+"</h6>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td>&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table bgcolor='' class='body-wrap'>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td align='' bgcolor='#FFFFFF' class='container'>\n" +
                "<div class='content'>\n" +
                "<table>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<h2><span style='font-size: 14pt;'>Ol&aacute;, <strong>"+ nome +"</strong>!</span></h2>\n" +
                "&nbsp;\n" +
                "<p>Gostaríamos de informá-lo(a) que sua solicitação de exame, com número de protocolo <b>"+protocolo+"</b>,  \n" +
                "está em análise. Em breve, enviaremos o resultado desta verificação.</p> " +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td>&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table bgcolor='#2E7CBD' class='head-wrap'>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td align='' class='header container'>\n" +
                "<div class='content' style='text-align: center;'>\n" +
                "<p style='color: #fff;'>Este e-mail foi enviado para "+ email +".<br />Caso queira, voc&ecirc; poder&aacute; entrar em contato a qualquer momento.<br />Fale conosco atrav&eacute;s do saudenaval@marinha.mil.br.</p>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td>&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</body>";

        helper.setText(body,true);
        javaMailSender.send(mimeMessage);
    }


    public void confirmationByMail(String message, String email) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom("reginaldo.elias@marinha.mil.br");

        helper.setTo(email);
        helper.setSubject("Informação da situação do Pedido de Exame");

        String body = "<head>\t<style type='text/css'>/* ------------------------------------- \n" +
                "\t\tGLOBAL \n" +
                "------------------------------------- */\n" +
                "* { \n" +
                "\tmargin:0;\n" +
                "\tpadding:0;\n" +
                "}\n" +
                "* { font-family: 'Open Sans', Helvetica, Arial, sans-serif; }\n" +
                "\n" +
                "img { \n" +
                "\tmax-width: 100%; \n" +
                "}\n" +
                ".collapse {\n" +
                "\tmargin:0;\n" +
                "\tpadding:0;\n" +
                "}\n" +
                "body {\n" +
                "\t-webkit-font-smoothing:antialiased; \n" +
                "\t-webkit-text-size-adjust:none; \n" +
                "\twidth: 100%!important; \n" +
                "\theight: 100%;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tELEMENTS \n" +
                "------------------------------------- */\n" +
                "a { color: #2BA6CB;}\n" +
                "\n" +
                ".btn {\n" +
                "\ttext-decoration:none;\n" +
                "\tcolor: #50514f;\n" +
                "\tbackground-color: #f1b90f;;\n" +
                "\tpadding:10px 16px;\n" +
                "\tfont-weight:bold;\n" +
                "\tmargin-right:10px;\n" +
                "\ttext-align:center;\n" +
                "\tcursor:pointer;\n" +
                "\tdisplay: inline-block;\n" +
                "}\n" +
                "\n" +
                "p.callout {\n" +
                "\tpadding:15px;\n" +
                "\tbackground-color:#ECF8FF;\n" +
                "\tmargin-bottom: 15px;\n" +
                "}\n" +
                ".callout a {\n" +
                "\tfont-weight:bold;\n" +
                "\tcolor: #2BA6CB;\n" +
                "}\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tHEADER \n" +
                "------------------------------------- */\n" +
                "table.head-wrap { width: 100%;}\n" +
                "\n" +
                ".header.container table td.logo { padding: 15px; }\n" +
                ".header.container table td.label { padding: 15px; padding-left:0px;}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tBODY \n" +
                "------------------------------------- */\n" +
                "table.body-wrap { width: 100%;}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tFOOTER \n" +
                "------------------------------------- */\n" +
                "table.footer-wrap { width: 100%;\tclear:both!important;\n" +
                "}\n" +
                ".footer-wrap .container td.content  p { border-top: 1px solid rgb(215,215,215); padding-top:15px;}\n" +
                ".footer-wrap .container td.content p {\n" +
                "\tfont-size:10px;\n" +
                "\tfont-weight: bold;\n" +
                "\t\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/* ------------------------------------- \n" +
                "\t\tTYPOGRAPHY \n" +
                "------------------------------------- */\n" +
                "h1,h2,h3,h4,h5,h6 {\n" +
                "font-family: 'Open Sans', Helvetica, Arial, sans-serif; line-height: 1.1; margin-bottom:15px; color:#000;\n" +
                "}\n" +
                "h1 small, h2 small, h3 small, h4 small, h5 small, h6 small { font-size: 60%; color: #6f6f6f; line-height: 0; text-transform: none; }\n" +
                "\n" +
                "h1 { font-weight:200; font-size: 44px;}\n" +
                "h2 { font-weight:200; font-size: 37px;}\n" +
                "h3 { font-weight:500; font-size: 27px;}\n" +
                "h4 { font-weight:500; font-size: 23px;}\n" +
                "h5 { font-weight:900; font-size: 17px;}\n" +
                "h6 { font-weight:900; font-size: 14px; text-transform: uppercase; color:#fff;}\n" +
                "\n" +
                ".collapse { margin:0!important;}\n" +
                "\n" +
                "p, ul { \n" +
                "\tmargin-bottom: 10px; \n" +
                "\tfont-weight: normal; \n" +
                "\tfont-size:14px; \n" +
                "\tline-height:1.6;\n" +
                "    text-align: justify;\n" +
                "}\n" +
                "p.lead { font-size:17px; }\n" +
                "p.last { margin-bottom:0px;}\n" +
                "\n" +
                "ul li {\n" +
                "\tmargin-left:5px;\n" +
                "\tlist-style-position: inside;\n" +
                "}\n" +
                "\n" +
                "/* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n" +
                ".container {\n" +
                "\tdisplay:block!important;\n" +
                "\tmax-width:600px!important;\n" +
                "\tmargin:0 auto!important; /* makes it centered */\n" +
                "\tclear:both!important;\n" +
                "}\n" +
                "\n" +
                "/* This should also be a block element, so that it will fill 100% of the .container */\n" +
                ".content {\n" +
                "\tpadding:15px;\n" +
                "\tmax-width:600px;\n" +
                "\tmargin:0 auto;\n" +
                "\tdisplay:block; \n" +
                "}\n" +
                "\n" +
                "/* Let's make sure tables in the content area are 100% wide */\n" +
                ".content table { width: 100%; }\n" +
                "\n" +
                "\n" +
                "/* Odds and ends */\n" +
                ".column {\n" +
                "\twidth: 300px;\n" +
                "\tfloat:left;\n" +
                "}\n" +
                ".column tr td { padding: 15px; }\n" +
                ".column-wrap { \n" +
                "\tpadding:0!important; \n" +
                "\tmargin:0 auto; \n" +
                "\tmax-width:600px!important;\n" +
                "}\n" +
                ".column table { width:100%;}\n" +
                ".social .column {\n" +
                "\twidth: 280px;\n" +
                "\tmin-width: 279px;\n" +
                "\tfloat:left;\n" +
                "}\n" +
                "\n" +
                "/* Be sure to place a .clear element after each set of columns, just to be safe */\n" +
                ".clear { display: block; clear: both; }\n" +
                "\n" +
                "@media only screen and (max-width: 600px) {\n" +
                "\t\n" +
                "\ta[class='btn'] { display:block!important; margin-bottom:10px!important; background-image:none!important; margin-right:0!important;}\n" +
                "\n" +
                "\tdiv[class='column'] { width: auto!important; float:none!important;}\n" +
                "\t\n" +
                "\ttable.social div[class='column'] {\n" +
                "\t\twidth:auto!important;\n" +
                "\t}\n" +
                "\n" +
                "}\n" +
                "\t</style>" +
                "" +
                "</head>" +
                "<body bgcolor='#FFFFFF' leftmargin='0' marginheight='0' marginwidth='0' topmargin='0' style=''>\n" +
                "<table bgcolor='#2E7CBD' class='head-wrap'>\n" +
                "<tbody class=''>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td align='' class='header container'>\n" +
                "<div class='content'>\n" +
                "<table bgcolor='#2E7CBD'>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td><a href='https://www.marinha.mil.br/saudenaval/'><img alt='' height='auto' src='https://www.marinha.mil.br/saudenaval/sites/www.marinha.mil.br.saudenaval/files/logo-especialidades.png' width='180' /></a></td>\n" +
                "<td align='right'>\n" +
                "<h6 class='collapse'>"+"Confirmação de Solicitação de Marcação de Exame"+"</h6>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td>&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table bgcolor='' class='body-wrap'>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td align='' bgcolor='#FFFFFF' class='container'>\n" +
                "<div class='content'>\n" +
                "<table>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                            message +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td>&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table bgcolor='#2E7CBD' class='head-wrap'>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>&nbsp;</td>\n" +
                "<td align='' class='header container'>\n" +
                "<div class='content' style='text-align: center;'>\n" +
                "<p style='color: #fff;'>Este e-mail foi enviado para "+ email +".<br />Caso queira, voc&ecirc; poder&aacute; entrar em contato a qualquer momento.<br />Fale conosco atrav&eacute;s do saudenaval@marinha.mil.br.</p>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td>&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</body>";

        helper.setText(body,true);
        javaMailSender.send(mimeMessage);

    }
}

package com.pqrs_backend.service;

import com.pqrs_backend.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Service
@Transactional
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String email;
    @Autowired
    private MessageUtil messageUtil;

    public String htmlRegistro(String nombre){
         String htmlEmail = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                 "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                 " <head>\n" +
                 "  <meta charset=\"UTF-8\">\n" +
                 "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                 "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                 "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                 "  <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                 "  <title>Registro</title><!--[if (mso 16)]>\n" +
                 "    <style type=\"text/css\">\n" +
                 "    a {text-decoration: none;}\n" +
                 "    </style>\n" +
                 "    <![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\n" +
                 "<xml>\n" +
                 "    <o:OfficeDocumentSettings>\n" +
                 "    <o:AllowPNG></o:AllowPNG>\n" +
                 "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                 "    </o:OfficeDocumentSettings>\n" +
                 "</xml>\n" +
                 "<![endif]--><!--[if !mso]><!-- -->\n" +
                 "  <link href=\"https://fonts.googleapis.com/css?family=Lato:400,400i,700,700i\" rel=\"stylesheet\"><!--<![endif]-->\n" +
                 "  <style type=\"text/css\">\n" +
                 "#outlook a {\n" +
                 "\tpadding:0;\n" +
                 "}\n" +
                 ".ExternalClass {\n" +
                 "\twidth:100%;\n" +
                 "}\n" +
                 ".ExternalClass,\n" +
                 ".ExternalClass p,\n" +
                 ".ExternalClass span,\n" +
                 ".ExternalClass font,\n" +
                 ".ExternalClass td,\n" +
                 ".ExternalClass div {\n" +
                 "\tline-height:100%;\n" +
                 "}\n" +
                 ".es-button {\n" +
                 "\tmso-style-priority:100!important;\n" +
                 "\ttext-decoration:none!important;\n" +
                 "}\n" +
                 "a[x-apple-data-detectors] {\n" +
                 "\tcolor:inherit!important;\n" +
                 "\ttext-decoration:none!important;\n" +
                 "\tfont-size:inherit!important;\n" +
                 "\tfont-family:inherit!important;\n" +
                 "\tfont-weight:inherit!important;\n" +
                 "\tline-height:inherit!important;\n" +
                 "}\n" +
                 ".es-desk-hidden {\n" +
                 "\tdisplay:none;\n" +
                 "\tfloat:left;\n" +
                 "\toverflow:hidden;\n" +
                 "\twidth:0;\n" +
                 "\tmax-height:0;\n" +
                 "\tline-height:0;\n" +
                 "\tmso-hide:all;\n" +
                 "}\n" +
                 "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120%!important } h1 { font-size:30px!important; text-align:center } h2 { font-size:26px!important; text-align:center } h3 { font-size:20px!important; text-align:center } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:16px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } a.es-button, button.es-button { font-size:20px!important; display:block!important; padding:10px 20px 10px 20px!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } }\n" +
                 "</style>\n" +
                 " </head>\n" +
                 " <body style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;padding:0;Margin:0\">\n" +
                 "  <div class=\"es-wrapper-color\" style=\"background-color:#F6F6F6\"><!--[if gte mso 9]>\n" +
                 "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                 "\t\t\t\t<v:fill type=\"tile\" color=\"#f6f6f6\"></v:fill>\n" +
                 "\t\t\t</v:background>\n" +
                 "\t\t<![endif]-->\n" +
                 "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#F6F6F6\">\n" +
                 "     <tr style=\"border-collapse:collapse\">\n" +
                 "      <td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                 "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                 "         <tr style=\"border-collapse:collapse\">\n" +
                 "          <td class=\"es-adaptive\" align=\"center\" style=\"padding:0;Margin:0\">\n" +
                 "           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                 "             <tr style=\"border-collapse:collapse\">\n" +
                 "              <td align=\"left\" style=\"padding:10px;Margin:0\"><!--[if mso]><table style=\"width:580px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:369px\" valign=\"top\"><![endif]-->\n" +
                 "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                 "                 <tr style=\"border-collapse:collapse\">\n" +
                 "                  <td class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:369px\">\n" +
                 "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "                     <tr style=\"border-collapse:collapse\">\n" +
                 "                      <td esdev-links-color=\"#999999\" class=\"es-infoblock es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#999999\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;color:#999999;font-size:12px\">UFPS CÚCUTA</p></td>\n" +
                 "                     </tr>\n" +
                 "                   </table></td>\n" +
                 "                 </tr>\n" +
                 "               </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:191px\" valign=\"top\"><![endif]-->\n" +
                 "               <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "                 <tr style=\"border-collapse:collapse\">\n" +
                 "                  <td align=\"left\" style=\"padding:0;Margin:0;width:191px\">\n" +
                 "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "                     <tr style=\"border-collapse:collapse\">\n" +
                 "                      <td esdev-links-color=\"#999999\" align=\"right\" class=\"es-infoblock es-m-txt-c\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#999999\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;color:#999999;font-size:12px\"><a href=\"mailto:sistemaspqrsufps@gmail.com?subject=%C2%A1Quiero%20m%C3%A1s%20informaci%C3%B3n%20acerca%20de%20PQRS%20UFPS!\" target=\"_blank\" class=\"view\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#999999;font-size:12px\">Correo</a></p></td>\n" +
                 "                     </tr>\n" +
                 "                   </table></td>\n" +
                 "                 </tr>\n" +
                 "               </table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                 "             </tr>\n" +
                 "           </table></td>\n" +
                 "         </tr>\n" +
                 "       </table>\n" +
                 "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                 "         <tr style=\"border-collapse:collapse\">\n" +
                 "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                 "           <table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#101727\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#101727;width:600px\">\n" +
                 "             <tr style=\"border-collapse:collapse\">\n" +
                 "              <td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                 "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "                 <tr style=\"border-collapse:collapse\">\n" +
                 "                  <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\n" +
                 "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "                     <tr style=\"border-collapse:collapse\">\n" +
                 "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://ingsistemas.cloud.ufps.edu.co/rsc/Sistema%20Documental/fondoescritorio.jpg\" alt=\"Happy Halloween\" title=\"Happy Halloween\" width=\"600\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                 "                     </tr>\n" +
                 "                   </table></td>\n" +
                 "                 </tr>\n" +
                 "               </table></td>\n" +
                 "             </tr>\n" +
                 "             <tr style=\"border-collapse:collapse\">\n" +
                 "              <td style=\"padding:0;Margin:0;padding-bottom:40px;padding-left:40px;padding-right:40px;background-color:#df3a1e\" bgcolor=\"#df3a1e\" align=\"left\">\n" +
                 "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "                 <tr style=\"border-collapse:collapse\">\n" +
                 "                  <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:520px\">\n" +
                 "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "                     <tr style=\"border-collapse:collapse\">\n" +
                 "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"padding:0;Margin:0\"><h3 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;font-size:30px;font-style:normal;font-weight:bold;color:#fff5c7\">¡Registro exitoso!</h3></td>\n" +
                 "                     </tr>\n" +
                 "                     <tr style=\"border-collapse:collapse\">\n" +
                 "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px\"><h1 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;font-size:30px;font-style:normal;font-weight:bold;color:#fff5c7\"><br></h1></td>\n" +
                 "                     </tr>\n" +
                 "                     <tr style=\"border-collapse:collapse\">\n" +
                 "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:10px;padding-right:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:21px;color:#fff5c7;font-size:14px\">Bienvenido " + nombre + " al PQRS de ingeniería de Sistemas UFPS, Ahora puedes hacer Peticiones, Quejas, Sugerencias, Reclamos. Y con gusto te ayudaremos con tus inquietudes y/o dudas.</p></td>\n" +
                 "                     </tr>\n" +
                 "                     <tr style=\"border-collapse:collapse\">\n" +
                 "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"padding:0;Margin:0\">\n" +
                 "                       <div style=\"color:#fff5c7;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:18px\">\n" +
                 "                        <em>&nbsp;LA UFPS SOY YO, ERES TÚ, SOMOS TODOS.</em>\n" +
                 "                       </div></td>\n" +
                 "                     </tr>\n" +
                 "                     <tr style=\"border-collapse:collapse\">\n" +
                 "                      <td align=\"center\" style=\"Margin:0;padding-left:10px;padding-right:10px;padding-top:20px;padding-bottom:20px\"><span class=\"es-button-border\" style=\"border-style:solid;border-color:#fff5c7;background:#fff5c7;border-width:0px;display:inline-block;border-radius:0px;width:auto;mso-border-alt:10px\"><a href=\"https://viewstripo.email/\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#333333;font-size:18px;display:inline-block;background:#fff5c7;border-radius:0px;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-weight:normal;font-style:normal;line-height:22px;width:auto;text-align:center;padding:10px 20px 10px 20px;border-color:#fff5c7\">Ingresa al sitio</a></span></td>\n" +
                 "                     </tr>\n" +
                 "                   </table></td>\n" +
                 "                 </tr>\n" +
                 "               </table></td>\n" +
                 "             </tr>\n" +
                 "           </table></td>\n" +
                 "         </tr>\n" +
                 "       </table>\n" +
                 "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                 "         <tr style=\"border-collapse:collapse\">\n" +
                 "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                 "           <table class=\"es-footer-body\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#EFEFEF;width:600px\">\n" +
                 "             <tr style=\"border-collapse:collapse\">\n" +
                 "              <td align=\"left\" style=\"padding:20px;Margin:0\"><!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:356px\" valign=\"top\"><![endif]-->\n" +
                 "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                 "                 <tr style=\"border-collapse:collapse\">\n" +
                 "                  <td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:356px\">\n" +
                 "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "                     <tr style=\"border-collapse:collapse\">\n" +
                 "                      <td esdev-links-color=\"#666666\" align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:28px;color:#666666;font-size:14px\">© 2023 PQRS UFPS. Todos los derechos reservados.<a target=\"_blank\" class=\"unsubscribe\" href=\"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"></a></p></td>\n" +
                 "                     </tr>\n" +
                 "                   </table></td>\n" +
                 "                 </tr>\n" +
                 "               </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:184px\" valign=\"top\"><![endif]-->\n" +
                 "               <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "                 <tr style=\"border-collapse:collapse\">\n" +
                 "                  <td align=\"left\" style=\"padding:0;Margin:0;width:184px\">\n" +
                 "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "                     <tr style=\"border-collapse:collapse\">\n" +
                 "                      <td esdev-links-color=\"#666666\" align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:28px;color:#666666;font-size:14px\">¿Preguntas<a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\">?</a></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:28px;color:#666666;font-size:14px\">sistemaspqrsufps@gmail.com</p></td>\n" +
                 "                     </tr>\n" +
                 "                     <tr style=\"border-collapse:collapse\">\n" +
                 "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-top:5px;font-size:0\">\n" +
                 "                       <table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                 "                         <tr style=\"border-collapse:collapse\">\n" +
                 "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><a href=\"https://twitter.com/UFPSCUCUTA\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img title=\"Twitter\" src=\"https://dghgjs.stripocdn.email/content/assets/img/social-icons/rounded-gray/twitter-rounded-gray.png\" alt=\"Tw\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                 "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><a href=\"https://www.facebook.com/Ufps.edu.co\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img title=\"Facebook\" src=\"https://dghgjs.stripocdn.email/content/assets/img/social-icons/rounded-gray/facebook-rounded-gray.png\" alt=\"Fb\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                 "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0\"><a href=\"https://www.youtube.com/@ufpscucutatv8254\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img title=\"Youtube\" src=\"https://dghgjs.stripocdn.email/content/assets/img/social-icons/rounded-gray/youtube-rounded-gray.png\" alt=\"Yt\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                 "                         </tr>\n" +
                 "                       </table></td>\n" +
                 "                     </tr>\n" +
                 "                   </table></td>\n" +
                 "                 </tr>\n" +
                 "               </table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                 "             </tr>\n" +
                 "           </table></td>\n" +
                 "         </tr>\n" +
                 "       </table></td>\n" +
                 "     </tr>\n" +
                 "   </table>\n" +
                 "  </div>\n" +
                 " </body>\n" +
                 "</html>";

         return htmlEmail;
    }

    public String htmlNuevaPregunta(String nombre, String estudiante){
        String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                " <head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "  <title>Notificacin pregunta</title><!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "    a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "    <o:OfficeDocumentSettings>\n" +
                "    <o:AllowPNG></o:AllowPNG>\n" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "    </o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]--><!--[if !mso]><!-- -->\n" +
                "  <link href=\"https://fonts.googleapis.com/css?family=Lato:400,400i,700,700i\" rel=\"stylesheet\"><!--<![endif]-->\n" +
                "  <style type=\"text/css\">\n" +
                "#outlook a {\n" +
                "\tpadding:0;\n" +
                "}\n" +
                ".ExternalClass {\n" +
                "\twidth:100%;\n" +
                "}\n" +
                ".ExternalClass,\n" +
                ".ExternalClass p,\n" +
                ".ExternalClass span,\n" +
                ".ExternalClass font,\n" +
                ".ExternalClass td,\n" +
                ".ExternalClass div {\n" +
                "\tline-height:100%;\n" +
                "}\n" +
                ".es-button {\n" +
                "\tmso-style-priority:100!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "\tcolor:inherit!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "\tfont-size:inherit!important;\n" +
                "\tfont-family:inherit!important;\n" +
                "\tfont-weight:inherit!important;\n" +
                "\tline-height:inherit!important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "\tdisplay:none;\n" +
                "\tfloat:left;\n" +
                "\toverflow:hidden;\n" +
                "\twidth:0;\n" +
                "\tmax-height:0;\n" +
                "\tline-height:0;\n" +
                "\tmso-hide:all;\n" +
                "}\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120%!important } h1 { font-size:30px!important; text-align:center } h2 { font-size:26px!important; text-align:center } h3 { font-size:20px!important; text-align:center } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:16px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } a.es-button, button.es-button { font-size:20px!important; display:block!important; padding:10px 20px 10px 20px!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } }\n" +
                "</style>\n" +
                " </head>\n" +
                " <body style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;padding:0;Margin:0\">\n" +
                "  <div class=\"es-wrapper-color\" style=\"background-color:#F6F6F6\"><!--[if gte mso 9]>\n" +
                "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "\t\t\t\t<v:fill type=\"tile\" color=\"#f6f6f6\"></v:fill>\n" +
                "\t\t\t</v:background>\n" +
                "\t\t<![endif]-->\n" +
                "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#F6F6F6\">\n" +
                "     <tr style=\"border-collapse:collapse\">\n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "         <tr style=\"border-collapse:collapse\">\n" +
                "          <td class=\"es-adaptive\" align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td align=\"left\" style=\"padding:10px;Margin:0\"><!--[if mso]><table style=\"width:580px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:369px\" valign=\"top\"><![endif]-->\n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:369px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#999999\" class=\"es-infoblock es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#999999\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;color:#999999;font-size:12px\">UFPS CÚCUTA</p></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:191px\" valign=\"top\"><![endif]-->\n" +
                "               <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:191px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#999999\" align=\"right\" class=\"es-infoblock es-m-txt-c\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#999999\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;color:#999999;font-size:12px\"><a href=\"mailto:sistemaspqrsufps@gmail.com?subject=%C2%A1Quiero%20m%C3%A1s%20informaci%C3%B3n%20acerca%20de%20PQRS%20UFPS!\" target=\"_blank\" class=\"view\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#999999;font-size:12px\">Correo</a></p></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table>\n" +
                "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "         <tr style=\"border-collapse:collapse\">\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#101727\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#101727;width:600px\">\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://ingsistemas.cloud.ufps.edu.co/rsc/Sistema%20Documental/fondoescritorio.jpg\" alt=\"Happy Halloween\" title=\"Happy Halloween\" width=\"600\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td style=\"padding:0;Margin:0;padding-bottom:40px;padding-left:40px;padding-right:40px;background-color:#df3a1e\" bgcolor=\"#df3a1e\" align=\"left\">\n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:520px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"padding:0;Margin:0\"><h3 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;font-size:30px;font-style:normal;font-weight:bold;color:#fff5c7\">¡Un usuario ha dejado una nueva pregunta!</h3></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px\"><h1 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;font-size:30px;font-style:normal;font-weight:bold;color:#fff5c7\"><br></h1></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:10px;padding-right:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:21px;color:#fff5c7;font-size:14px\">Hola " + nombre + ", el usuario " + estudiante + " ha dejado una nueva pregunta. Por favor revisarla pronto.</p></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "                       <div style=\"color:#fff5c7;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:18px\">\n" +
                "                        <em>&nbsp;LA UFPS SOY YO, ERES TÚ, SOMOS TODOS.</em>\n" +
                "                       </div></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table>\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "         <tr style=\"border-collapse:collapse\">\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table class=\"es-footer-body\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#EFEFEF;width:600px\">\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td align=\"left\" style=\"padding:20px;Margin:0\"><!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:356px\" valign=\"top\"><![endif]-->\n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:356px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#666666\" align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:28px;color:#666666;font-size:14px\">© 2023 PQRS UFPS. Todos los derechos reservados.<a target=\"_blank\" class=\"unsubscribe\" href=\"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"></a></p></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:184px\" valign=\"top\"><![endif]-->\n" +
                "               <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:184px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#666666\" align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:28px;color:#666666;font-size:14px\">¿Preguntas<a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\">?</a></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:28px;color:#666666;font-size:14px\">sistemaspqrsufps@gmail.com</p></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-top:5px;font-size:0\">\n" +
                "                       <table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr style=\"border-collapse:collapse\">\n" +
                "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><a href=\"https://twitter.com/UFPSCUCUTA\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img title=\"Twitter\" src=\"https://dghgjs.stripocdn.email/content/assets/img/social-icons/rounded-gray/twitter-rounded-gray.png\" alt=\"Tw\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><a href=\"https://www.facebook.com/Ufps.edu.co\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img title=\"Facebook\" src=\"https://dghgjs.stripocdn.email/content/assets/img/social-icons/rounded-gray/facebook-rounded-gray.png\" alt=\"Fb\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0\"><a href=\"https://www.youtube.com/@ufpscucutatv8254\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img title=\"Youtube\" src=\"https://dghgjs.stripocdn.email/content/assets/img/social-icons/rounded-gray/youtube-rounded-gray.png\" alt=\"Yt\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table></td>\n" +
                "     </tr>\n" +
                "   </table>\n" +
                "  </div>\n" +
                " </body>\n" +
                "</html>";

        return  html;
    }

    public String htmlRespuesta(String nombre,String titulo){
        String htmlToken = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                " <head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "  <title>Notificacin respuesta</title><!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "    a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "    <o:OfficeDocumentSettings>\n" +
                "    <o:AllowPNG></o:AllowPNG>\n" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "    </o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]--><!--[if !mso]><!-- -->\n" +
                "  <link href=\"https://fonts.googleapis.com/css?family=Lato:400,400i,700,700i\" rel=\"stylesheet\"><!--<![endif]-->\n" +
                "  <style type=\"text/css\">\n" +
                "#outlook a {\n" +
                "\tpadding:0;\n" +
                "}\n" +
                ".ExternalClass {\n" +
                "\twidth:100%;\n" +
                "}\n" +
                ".ExternalClass,\n" +
                ".ExternalClass p,\n" +
                ".ExternalClass span,\n" +
                ".ExternalClass font,\n" +
                ".ExternalClass td,\n" +
                ".ExternalClass div {\n" +
                "\tline-height:100%;\n" +
                "}\n" +
                ".es-button {\n" +
                "\tmso-style-priority:100!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "\tcolor:inherit!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "\tfont-size:inherit!important;\n" +
                "\tfont-family:inherit!important;\n" +
                "\tfont-weight:inherit!important;\n" +
                "\tline-height:inherit!important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "\tdisplay:none;\n" +
                "\tfloat:left;\n" +
                "\toverflow:hidden;\n" +
                "\twidth:0;\n" +
                "\tmax-height:0;\n" +
                "\tline-height:0;\n" +
                "\tmso-hide:all;\n" +
                "}\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120%!important } h1 { font-size:30px!important; text-align:center } h2 { font-size:26px!important; text-align:center } h3 { font-size:20px!important; text-align:center } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:16px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } a.es-button, button.es-button { font-size:20px!important; display:block!important; padding:10px 20px 10px 20px!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } }\n" +
                "</style>\n" +
                " </head>\n" +
                " <body style=\"width:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;padding:0;Margin:0\">\n" +
                "  <div class=\"es-wrapper-color\" style=\"background-color:#F6F6F6\"><!--[if gte mso 9]>\n" +
                "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "\t\t\t\t<v:fill type=\"tile\" color=\"#f6f6f6\"></v:fill>\n" +
                "\t\t\t</v:background>\n" +
                "\t\t<![endif]-->\n" +
                "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#F6F6F6\">\n" +
                "     <tr style=\"border-collapse:collapse\">\n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "         <tr style=\"border-collapse:collapse\">\n" +
                "          <td class=\"es-adaptive\" align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td align=\"left\" style=\"padding:10px;Margin:0\"><!--[if mso]><table style=\"width:580px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:369px\" valign=\"top\"><![endif]-->\n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:369px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#999999\" class=\"es-infoblock es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#999999\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;color:#999999;font-size:12px\">UFPS CÚCUTA</p></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:191px\" valign=\"top\"><![endif]-->\n" +
                "               <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:191px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#999999\" align=\"right\" class=\"es-infoblock es-m-txt-c\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#999999\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;color:#999999;font-size:12px\"><a href=\"mailto:sistemaspqrsufps@gmail.com?subject=%C2%A1Quiero%20m%C3%A1s%20informaci%C3%B3n%20acerca%20de%20PQRS%20UFPS!\" target=\"_blank\" class=\"view\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#999999;font-size:12px\">Correo</a></p></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table>\n" +
                "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "         <tr style=\"border-collapse:collapse\">\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#101727\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#101727;width:600px\">\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://ingsistemas.cloud.ufps.edu.co/rsc/Sistema%20Documental/fondoescritorio.jpg\" alt=\"Happy Halloween\" title=\"Happy Halloween\" width=\"600\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td style=\"padding:0;Margin:0;padding-bottom:40px;padding-left:40px;padding-right:40px;background-color:#df3a1e\" bgcolor=\"#df3a1e\" align=\"left\">\n" +
                "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:520px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"padding:0;Margin:0\"><h3 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;font-size:30px;font-style:normal;font-weight:bold;color:#fff5c7\">¡Se ha respondido tu pregunta!</h3></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px\"><h1 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;font-size:30px;font-style:normal;font-weight:bold;color:#fff5c7\"><br></h1></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:10px;padding-right:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:21px;color:#fff5c7;font-size:14px\">Hola "+ nombre + ", el presente correo es para infrormarte que tu pregunta con titulo (" + titulo +") ha sido respondida.</p></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#fff5c7\" align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "                       <div style=\"color:#fff5c7;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:18px\">\n" +
                "                        <em>&nbsp;LA UFPS SOY YO, ERES TÚ, SOMOS TODOS.</em>\n" +
                "                       </div></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table>\n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "         <tr style=\"border-collapse:collapse\">\n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "           <table class=\"es-footer-body\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#EFEFEF;width:600px\">\n" +
                "             <tr style=\"border-collapse:collapse\">\n" +
                "              <td align=\"left\" style=\"padding:20px;Margin:0\"><!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:356px\" valign=\"top\"><![endif]-->\n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:356px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#666666\" align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:28px;color:#666666;font-size:14px\">© 2023 PQRS UFPS. Todos los derechos reservados.<a target=\"_blank\" class=\"unsubscribe\" href=\"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"></a></p></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:184px\" valign=\"top\"><![endif]-->\n" +
                "               <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                 <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:184px\">\n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td esdev-links-color=\"#666666\" align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:28px;color:#666666;font-size:14px\">¿Preguntas<a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\">?</a></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:28px;color:#666666;font-size:14px\">sistemaspqrsufps@gmail.com</p></td>\n" +
                "                     </tr>\n" +
                "                     <tr style=\"border-collapse:collapse\">\n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-top:5px;font-size:0\">\n" +
                "                       <table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                         <tr style=\"border-collapse:collapse\">\n" +
                "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><a href=\"https://twitter.com/UFPSCUCUTA\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img title=\"Twitter\" src=\"https://dghgjs.stripocdn.email/content/assets/img/social-icons/rounded-gray/twitter-rounded-gray.png\" alt=\"Tw\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><a href=\"https://www.facebook.com/Ufps.edu.co\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img title=\"Facebook\" src=\"https://dghgjs.stripocdn.email/content/assets/img/social-icons/rounded-gray/facebook-rounded-gray.png\" alt=\"Fb\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0\"><a href=\"https://www.youtube.com/@ufpscucutatv8254\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#666666;font-size:14px\"><img title=\"Youtube\" src=\"https://dghgjs.stripocdn.email/content/assets/img/social-icons/rounded-gray/youtube-rounded-gray.png\" alt=\"Yt\" width=\"24\" height=\"24\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td>\n" +
                "                         </tr>\n" +
                "                       </table></td>\n" +
                "                     </tr>\n" +
                "                   </table></td>\n" +
                "                 </tr>\n" +
                "               </table><!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "             </tr>\n" +
                "           </table></td>\n" +
                "         </tr>\n" +
                "       </table></td>\n" +
                "     </tr>\n" +
                "   </table>\n" +
                "  </div>\n" +
                " </body>\n" +
                "</html>";
        return htmlToken;
    }

    public void emailRegistro(String emailTo,String nombre){
        try {
            String htmlEmail = htmlRegistro(nombre);
            sendListEmail(emailTo,"¡Registro exitoso!",htmlEmail);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void emailNuevaPregunta(String emailTo,String nombre, String estudiante){
        try {
            String htmlEmail = htmlNuevaPregunta(nombre, estudiante);
            sendListEmail(emailTo,"¡Nueva pregunta!",htmlEmail);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void emailRespuesta(String emailTo,String nombre, String titulo){
        try {
            String htmlEmail = htmlRespuesta(nombre, titulo);
            sendListEmail(emailTo,"¡Se ha respondido tu pregunta!",htmlEmail);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void sendListEmail(String emailTo, String subject,String html){
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(email);
            helper.setTo(emailTo);
            helper.setSubject(subject);
            helper.setText(html,true);
            javaMailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

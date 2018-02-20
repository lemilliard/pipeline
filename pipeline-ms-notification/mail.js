var mailer = require("nodemailer");

var smtpTransport = mailer.createTransport("SMTP",{
    service: "Gmail",
    auth: {
        user: "lesalsaciens.contact@gmail.com",
        pass: "pastressecure"
    }
});

var mail = {
    from: "lesalsaciens.contact@gmail.com",
    to: "ludovic.bouvier.pro@gmail.com",
    subject: "Pipeline Email",
    html: "C'est Pipeline",
    attachments: [
        {
          filePath: 'leCheminDuFichierAEnvoyer'
        },
    ]
}

smtpTransport.sendMail(mail, function(error, response){
    if(error){
        console.log("Erreur lors de l'envoie du mail!");
        console.log(error);
    }else{
        console.log("Mail envoyé avec succès!")
    }
    smtpTransport.close();
});
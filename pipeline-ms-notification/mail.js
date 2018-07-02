var mailer = require("nodemailer");
var express = require('express');
var app = express();
var router = express.Router();
var parser = require('body-parser');

app.listen(8091, function () {
	console.log("Express Started on Port 8080");
});

app.use(router);
app.use(parser.json())
app.use(parser.urlencoded({ extended: true }));

app.post("/mail", function (request, res) {
	var smtpTransport = mailer.createTransport("SMTP", {
		service: "Gmail",
		auth: {
			user: "lesalsaciens.contact@gmail.com",
			pass: "pastressecure"
		}
	});

	var mail = {
		from: "lesalsaciens.contact@gmail.com",
		to: request.body.to,
		subject: request.body.subject,
		html: request.body.body,
	}
	if (request.body.attachments != null) {
		mail = {
			attachments: [
				{
					filePath: request.body.attachments
				},
			]
		}
	}

	smtpTransport.sendMail(mail, function (error, response) {
		if (error) {
			console.log("Erreur lors de l'envoie du mail!");
			console.log(error);
			res.sendStatus(400);
		} else {
			console.log("Mail envoyé avec succès!")
			res.sendStatus(200);
		}
		smtpTransport.close();
	});
});
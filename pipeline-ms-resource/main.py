import cx_Oracle
from flask import Flask
app = Flask(__name__)

address = "home.thomaskint.com"
port = 1521
sid = "orcl"
dsn_tns = cx_Oracle.makedsn(address, port, sid)
con = cx_Oracle.connect("pipeline", "password", dsn_tns)

@app.route("/")
def hello():
    return "Hello World!"

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)
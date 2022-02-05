from flask import Flask, request
from flask_restful import Resource, Api
from sqlalchemy import create_engine
from json import dumps
from flask.ext.jsonpify import jsonify

db_connect = create_engine('mysql://waduser:waduser01@localhost/brpgegevens')
app = Flask(__name__)
api = Api(app)

class BPR_Persoon(Resource):
    def get(self, BSNNummer):
        conn = db_connect.connect()
        query = conn.execute("select * from brpPersoon where bsnNummer =%s " %int(BSNNummer))
        result = [dict(zip(query.keys() ,i)) for i in query.cursor]
        foo = jsonify(result[0])
        return foo

api.add_resource(BPR_Persoon, '/PersoonsGegevens/<BSNNummer>')

if __name__ == '__main__':
     print('Now serving on port 5002')
     app.run(port='5002')

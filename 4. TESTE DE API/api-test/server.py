import logging
from flask import Flask, request, jsonify
import pandas as pd
from flask_cors import CORS
import numpy as np
logging.basicConfig(level=logging.INFO)

app = Flask(__name__)
CORS(app) 

def load_csv(filepath: str) -> pd.DataFrame:
    """Load a CSV file with specified settings."""
    try:
        data = pd.read_csv(filepath, sep=';', encoding='utf-8')
        logging.info("Available columns: %s", data.columns.tolist())
        return data
    except Exception as e:
        logging.error("Failed to load CSV file: %s", e)
        return pd.DataFrame()  

df = load_csv('Relatorio_cadop.csv')

@app.route('/search', methods=['GET'])
def search_operators():
    query = request.args.get('query', '').strip().lower()
    if not query:
        return jsonify([])

    mask = df.apply(
        lambda row: row.astype(str).str.lower().str.contains(query).any(),
        axis=1
    )
    filtered_df = df[mask]

    if not filtered_df.empty:
        display_columns = df.columns.tolist()
        filtered_df = filtered_df[display_columns]

    filtered_df = filtered_df.replace({np.nan: None})

    return jsonify(filtered_df.to_dict('records'))

if __name__ == '__main__':
    app.run(debug=True, port=5000)

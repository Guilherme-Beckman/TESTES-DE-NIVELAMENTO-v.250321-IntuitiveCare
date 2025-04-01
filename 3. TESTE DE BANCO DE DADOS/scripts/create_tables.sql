CREATE TABLE operadoras (
    registro_ans VARCHAR(20) PRIMARY KEY,
    cnpj VARCHAR(20),
    razao_social VARCHAR(255),
    nome_fantasia VARCHAR(255),
    modalidade VARCHAR(100),
    logradouro VARCHAR(255),
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf VARCHAR(2),
    cep VARCHAR(10),
    ddd VARCHAR(5),
    telefone VARCHAR(20),
    fax VARCHAR(20),
    endereco_eletronico VARCHAR(100),
    representante VARCHAR(255),
    cargo_representante VARCHAR(100),
    regiao_comercializacao INTEGER,
    data_registro_ans DATE
);
CREATE TABLE demonstracoes_contabeis (
    id SERIAL PRIMARY KEY,
    data DATE,
    reg_ans VARCHAR(20),
    cd_conta_contabil VARCHAR(20),
    descricao VARCHAR(255),
    vl_saldo_inicial double precision,
    vl_saldo_final double precision,
    FOREIGN KEY (reg_ans) REFERENCES operadoras(registro_ans)
);

CREATE INDEX idx_demonstracoes_reg_ans ON demonstracoes_contabeis(reg_ans);
CREATE INDEX idx_demonstracoes_conta ON demonstracoes_contabeis(cd_conta_contabil);
CREATE INDEX idx_demonstracoes_data ON demonstracoes_contabeis(data);

INSERT INTO operadoras (registro_ans, cnpj, razao_social, nome_fantasia, modalidade, logradouro, numero, complemento, bairro, cidade, uf, cep, ddd, telefone, fax, endereco_eletronico, representante, cargo_representante, regiao_comercializacao, data_registro_ans)
VALUES (
    'Sem Registro', 'Sem CNPJ', 'Sem Razão Social', 'Sem Nome Fantasia', 'Sem Modalidade',
    'Sem Logradouro', 'Sem Número', 'Sem Complemento', 'Sem Bairro', 'Sem Cidade', 'UF',
    'Sem CEP', '000', 'Sem Telefone', 'Sem Fax', 'Sem Endereço Eletrônico',
    'Sem Representante', 'Sem Cargo Representante', 0, '2023-10-10'
);
CREATE OR REPLACE FUNCTION trg_set_reg_ans_default()
RETURNS trigger AS $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM operadoras WHERE registro_ans = NEW.reg_ans
  ) THEN
    NEW.reg_ans := 'Sem Registro';
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;


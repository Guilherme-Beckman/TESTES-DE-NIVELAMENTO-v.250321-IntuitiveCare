COPY operadoras (
    registro_ans, cnpj, razao_social, nome_fantasia, modalidade, 
    logradouro, numero, complemento, bairro, cidade, uf, cep, 
    ddd, telefone, fax, endereco_eletronico, representante, 
    cargo_representante, regiao_comercializacao, data_registro_ans
)
FROM '/tmp/teste-3/Dados cadastrais das Operadoras Ativas na ANS/Relatorio_cadop.csv'
WITH (
    FORMAT CSV,
    HEADER,
    DELIMITER ';',
    ENCODING 'UTF8',
    QUOTE '"',
    NULL ''
);

COPY demonstracoes_contabeis (
    data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final
)
FROM PROGRAM 'sed "s/\\([0-9]\\),\\([0-9]\\)/\\1.\\2/g" "/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/1T2023.csv"'
WITH (
    FORMAT CSV,
    HEADER,
    DELIMITER ';',
    ENCODING 'UTF8',
    NULL ''
);

COPY demonstracoes_contabeis (
    data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final
)
FROM PROGRAM 'sed "s/\\([0-9]\\),\\([0-9]\\)/\\1.\\2/g" "/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/2T2023.csv"'
WITH (
    FORMAT CSV,
    HEADER,
    DELIMITER ';',
    ENCODING 'UTF8',
    NULL ''
);

COPY demonstracoes_contabeis (
    data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final
)
FROM PROGRAM 'sed "s/\\([0-9]\\),\\([0-9]\\)/\\1.\\2/g" "/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/3T2023.csv"'
WITH (
    FORMAT CSV,
    HEADER,
    DELIMITER ';',
    ENCODING 'UTF8',
    NULL ''
);

COPY demonstracoes_contabeis (
    data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final
)
FROM PROGRAM 'sed "s/\\([0-9]\\),\\([0-9]\\)/\\1.\\2/g" "/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/4T2023.csv"'
WITH (
    FORMAT CSV,
    HEADER,
    DELIMITER ';',
    ENCODING 'UTF8',
    NULL ''
);

COPY demonstracoes_contabeis (
    data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final
)
FROM PROGRAM 'sed "s/\\([0-9]\\),\\([0-9]\\)/\\1.\\2/g" "/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/1T2024.csv"'
WITH (
    FORMAT CSV,
    HEADER,
    DELIMITER ';',
    ENCODING 'UTF8',
    NULL ''
);

COPY demonstracoes_contabeis (
    data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final
)
FROM PROGRAM 'sed "s/\\([0-9]\\),\\([0-9]\\)/\\1.\\2/g" "/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/2T2024.csv"'
WITH (
    FORMAT CSV,
    HEADER,
    DELIMITER ';',
    ENCODING 'UTF8',
    NULL ''
);

COPY demonstracoes_contabeis (
    data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final
)
FROM PROGRAM 'sed "s/\\([0-9]\\),\\([0-9]\\)/\\1.\\2/g" "/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/3T2024.csv"'
WITH (
    FORMAT CSV,
    HEADER,
    DELIMITER ';',
    ENCODING 'UTF8',
    NULL ''
);

COPY demonstracoes_contabeis (
    data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final
)
FROM PROGRAM 'sed "s/\\([0-9]\\),\\([0-9]\\)/\\1.\\2/g" "/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/4T2024.csv"'
WITH (
    FORMAT CSV,
    HEADER,
    DELIMITER ';',
    ENCODING 'UTF8',
    NULL ''
);

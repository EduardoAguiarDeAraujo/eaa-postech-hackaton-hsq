INSERT INTO enderecos (id, logradouro, numero, bairro, cidade, estado, cep) VALUES (1,'Avenida Brasil', '1500', 'Centro', 'Rio de Janeiro', 'RJ', '20040-000');
INSERT INTO enderecos (id, logradouro, numero, bairro, cidade, estado, cep) VALUES (2,'Rua das Flores', 'SN', 'Jardim América', 'São Paulo', 'SP', '01234-567');
INSERT INTO enderecos (id, logradouro, numero, bairro, cidade, estado, cep) VALUES (3,'Setor Comercial Sul', 'Quadra 5', 'Asa Sul', 'Brasília', 'DF', '70305-000');
INSERT INTO enderecos (id, logradouro, numero, bairro, cidade, estado, cep) VALUES (4,'Pedro Fioretti', '1025', 'Centro', 'Osasco', 'SP', '06001-010');
INSERT INTO enderecos (id, logradouro, numero, bairro, cidade, estado, cep) VALUES (5,'Av. Santo Antônio', '850', 'Santo Antônio', 'Osasco', 'SP', '06101-012');
INSERT INTO enderecos (id, logradouro, numero, bairro, cidade, estado, cep) VALUES (6,'Av. Edgard Oliveira', '125', 'Quitaúna', 'Osasco', 'SP', '06186-012');
INSERT INTO enderecos (id, logradouro, numero, bairro, cidade, estado, cep) VALUES (7,'Av. Rio Paranapanema', '125', 'Jardim Piratininga', 'Osasco', 'SP', '06210-012');

INSERT INTO especialidades (id, nome, descricao) VALUES (1,'Clínica Médica', 'Atendimento geral e acompanhamento preventivo.');
INSERT INTO especialidades (id, nome, descricao) VALUES (2,'Pediatria', 'Cuidado especializado para crianças e adolescentes.');
INSERT INTO especialidades (id, nome, descricao) VALUES (3,'Ortopedia', 'Tratamento de lesões ósseas e musculares.');
INSERT INTO especialidades (id, nome, descricao) VALUES (4,'Ginecologia', 'Saúde reprodutiva e exames preventivos femininos.');
INSERT INTO especialidades (id, nome, descricao) VALUES (5,'Cardiologia', 'Diagnóstico e tratamento de doenças do coração.');
INSERT INTO especialidades (id, nome, descricao) VALUES (6,'Endocrinolocia', 'Diagnóstico e tratamento de doenças do sistema endócrino.');

INSERT INTO unidades_saude (id, cnes, nome_fantasia, razao_social, endereco_id) VALUES (1,'1234567', 'Hospital Regional Central', 'Instituto de Saúde do Estado S.A.', 1);
INSERT INTO unidades_saude (id, cnes, nome_fantasia, razao_social, endereco_id) VALUES (2,'2345678', 'UBS Jardim das Palmeiras', 'Prefeitura Municipal de Saúde', 2);
INSERT INTO unidades_saude (id, cnes, nome_fantasia, razao_social, endereco_id) VALUES (3,'3456789', 'UPA Asa Sul 24h', 'Fundo Nacional de Saúde', 3);
INSERT INTO unidades_saude (id, cnes, nome_fantasia, razao_social, endereco_id) VALUES (4,'3456790', 'UPA Osasco Centro', 'Fundo Nacional de Saúde - Osasco Centro', 4);
INSERT INTO unidades_saude (id, cnes, nome_fantasia, razao_social, endereco_id) VALUES (5,'3456791', 'UPA Santo Antônio', 'Fundo Nacional de Saúde - Osasco Santo Antônio', 5);
INSERT INTO unidades_saude (id, cnes, nome_fantasia, razao_social, endereco_id) VALUES (6,'3456792', 'UPA Quitaúna', 'Fundo Nacional de Saúde - Osasco Quitaúna', 6);
INSERT INTO unidades_saude (id, cnes, nome_fantasia, razao_social, endereco_id) VALUES (7,'3456793', 'UPA Piratininga', 'Fundo Nacional de Saúde - Osasco Piratininga', 7);

INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (1, 1);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (1, 3);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (1, 5);

INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (2, 1);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (2, 2);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (2, 4);

INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (3, 1);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (3, 2);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (4, 1);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (4, 2);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (4, 3);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (4, 4);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (4, 5);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (4, 6);

INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (5, 1);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (5, 5);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (5, 6);

INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (6, 1);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (6, 2);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (6, 3);

INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (7, 1);
INSERT INTO unidade_especialidade (unidade_id, especialidade_id) VALUES (7, 2);

INSERT INTO pacientes (cns, nome, data_nascimento, cpf, endereco_id) VALUES ('700012345678901', 'João da Silva', '1985-05-20', '12345678901', 1);
INSERT INTO pacientes (cns, nome, data_nascimento, cpf, endereco_id) VALUES ('700098765432100', 'Maria Oliveira', '1992-10-12', '98765432100', 2);
INSERT INTO pacientes (cns, nome, data_nascimento, cpf, endereco_id) VALUES ('700055566677788', 'Carlos Eduardo Santos', '1970-01-30', '55566677788', 3);

INSERT INTO profissionais (nome, registro_conselho, especialidade_id) VALUES ('Dr. Ricardo Arantes', 'CRM/RJ 123456', 5); -- Cardiologista
INSERT INTO profissionais (nome, registro_conselho, especialidade_id) VALUES ('Dra. Ana Beatriz', 'CRM/SP 654321', 2); -- Pediatra
INSERT INTO profissionais (nome, registro_conselho, especialidade_id) VALUES ('Dr. Sérgio Morocha', 'CRM/DF 998877', 1); -- Clínico Geral

INSERT INTO agendamentos (paciente_id, unidade_saude_id, especialidade_id, data_solicitacao, data_agendamento, status, justificativa_medica) VALUES (1, 2, 5, '2023-10-20 08:30:00', '2023-11-05 14:00:00', 'AGENDADO', 'Paciente apresenta palpitações e histórico familiar de hipertensão.');
INSERT INTO agendamentos (paciente_id, unidade_saude_id, especialidade_id, data_solicitacao, data_agendamento, status, justificativa_medica) VALUES (2, 2, 2, '2023-10-20 08:30:00', '2023-11-05 14:00:00', 'AGENDADO', 'Consulta de rotina pediátrica - 6 meses.');
INSERT INTO agendamentos (paciente_id, unidade_saude_id, especialidade_id, data_solicitacao, data_agendamento, status, justificativa_medica) VALUES (3, 1,  5, '2023-09-15 11:20:00', '2023-10-10 09:00:00', 'REALIZADO', 'Encaminhamento pós-infarto para acompanhamento.');

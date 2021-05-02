CREATE SEQUENCE IF NOT EXISTS FormalParameterGroupIdSequence
INCREMENT 1
START 1;

CREATE SEQUENCE IF NOT EXISTS FactParameterGroupIdSequence
INCREMENT 1
START 1;

INSERT INTO "parameter_data_type" ("name") VALUES
('String'),
('Number'),
('Time'),
('Boolean'),
('Date'),
('Item');

INSERT INTO "property" ("code", "is_note", "name") VALUES
('1', true,	'Property1'),
('2', true,	'Property2'),
('3', true,	'Property3');

INSERT INTO "strain_genus" ("name") VALUES
('Genus1'),
('Genus2'),
('Genus3');

INSERT INTO "strain_type" ("name", "genus_id") VALUES
('Type1',	1),
('Type2',	2),
('Type3',	3);

INSERT INTO "formal_parameter" ("group_id", "name", "parameter_data_type_id", "property_id") VALUES
(NULL, 'Val1',	1,	1),
(1,	'',	2,	1),
(1,	'23',	3,	2);

INSERT INTO "strain" ("collection_index", "creator", "date_added", "date_receiving", "name", "obtaining_method", "source", "type_id") VALUES
('1',	'2',	'2021-04-15',	'2021-04-15',	'Test1',	'Free',	'Src',	1),
('22',	'22',	'2021-04-15',	'2021-04-15',	'22',	'22',	'Src',	1);

INSERT INTO "fact_parameter" ("group_id", "reserve", "value", "formal_parameter_id", "strain_id") VALUES
(1,	NULL,	'123',	1,	1),
(1,	NULL,	'111',	2,	1),
(1,	NULL,	'333',	1,	1),
(NULL,	NULL,	'131',	2,	2);
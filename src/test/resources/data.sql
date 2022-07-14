CREATE SCHEMA IF NOT EXISTS IBS;
SET SCHEMA IBS;
CREATE TABLE IF NOT EXISTS "IBS"."CLASSES"
   (	"ID" VARCHAR2(30),
	"NAME" VARCHAR2(128),
	"ENTITY_ID" VARCHAR2(30),
	"PARENT_ID" VARCHAR2(30),
	"HAS_INSTANCES" VARCHAR2(1) DEFAULT '0',
	"INIT_STATE_ID" VARCHAR2(30),
	"SHORT_NAME" VARCHAR2(40),
	"AGREGATE" VARCHAR2(30),
	"BASE_CLASS_ID" VARCHAR2(16) ,
	"TARGET_CLASS_ID" VARCHAR2(30),
	"CRITERIA_ID" VARCHAR2(30),
	"DATA_SIZE" NUMBER,
	"DATA_PRECISION" NUMBER,
	"MIN_VALUE" VARCHAR2(2000),
	"MAX_VALUE" VARCHAR2(2000),
	"DEFAULT_VALUE" VARCHAR2(2000),
	"AUTONOMOUS" VARCHAR2(1) DEFAULT '0',
	"IS_COLLECTION" VARCHAR2(1) DEFAULT '0',
	"MODIFIED" DATE,
	"PAD_STYLE" VARCHAR2(1),
	"PAD_CHAR" VARCHAR2(1),
	"PAD_LENGTH" NUMBER,
	"INTERFACE" VARCHAR2(128),
	"KERNEL" VARCHAR2(1) DEFAULT '0',
	"PARAM_GROUP" VARCHAR2(16),
	"TAG" VARCHAR2(32),
	"VALIDATOR_ID" VARCHAR2(30),
	"INPUT_MASK" VARCHAR2(40),
	"TRIGGER_ID" VARCHAR2(30),
	"STORAGE_GROUP" VARCHAR2(30),
	"INIT_METHOD_ID" VARCHAR2(30),
	"LOB_STORAGE_GROUP" VARCHAR2(30),
	"TEMP_TYPE" VARCHAR2(1),
	"HAS_TYPE" VARCHAR2(1) DEFAULT '0',
	"KEY_ATTR" VARCHAR2(30),
	"DATA_PRECISION_MIN" NUMBER,
	"PROPERTIES" VARCHAR2(2000)
   );

CREATE TABLE IF NOT EXISTS "IBS"."SOURCES"
    (   "NAME" VARCHAR2(30),
    "TYPE" VARCHAR2(12),
    "LINE" NUMBER,
    "TEXT" VARCHAR2(2000)
   );


INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('1', 'TYP1',  1, 'строка1');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('1', 'TYP1',  2, 'строка2');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('1', 'TYP1',  3, null);
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('1', 'TYP1',  4, 'строка4');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('1', 'TYP2',  1, 'строка1');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('1', 'TYP2',  2, 'строка2');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('2', 'TYP1',  1, 'строка1');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('2', 'TYP1',  2, 'строка2');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('2', 'TYP3',  1, 'строка1');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('2', 'TYP3',  2, 'строка2');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('2', 'TYP3',  3, 'строка3');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('3', 'TYP3',  1, 'строка1');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('4', 'TYP4',  1, null);
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('4', 'TYP4',  2, 'строка2');
INSERT INTO IBS.SOURCES (NAME, TYPE, LINE, TEXT) VALUES('4', 'TYP4',  3, 'строка3');



INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('WIO_OPIS', 'Тип аккредитива', 'TYPE', null, '1', null, '0000000000100000000000000', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('2006-07-27 14:47:16', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ACCR_TYPE#INTERFACE.CLASS#ACCR_TYPE', '0', null, null, null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 5|');
INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('WIO_OPIS1', 'Виды связанных счетов', 'TYPE', null, '1', null, '0000000000100000000000000', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('2000-05-19 15:00:04', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ACC_LINK#INTERFACE.CLASS#ACC_LINK', '0', null, null, null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 26|');
INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('ACC_NUM_POOL', 'Открытые счета: Пулы счетов', 'TYPE', null, '1', null, '0100000000100000002100000', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('2016-03-04 13:08:40', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ACC_NUM_POOL#INTERFACE.CLASS#ACC_NUM_POOL', '0', null, null, null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 2343|');
INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('ACC_OPER', 'Операции по счету', 'TYPE', null, '1', null, '0000000000100000000000000', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('1998-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ACC_OPER#INTERFACE.CLASS#ACC_OPER', '0', null, 'CORE', null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 24|');
INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('ACC_PATTERN', 'ОП Шаблоны счетов', 'TYPE', null, '1', null, '0000000000100100000000010', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('2020-09-14 16:52:35', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ACC_PATTERN#INTERFACE.CLASS#ACC_PATTERN', '0', 'REPB_CB', null, null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 331797|');
INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('ACC_PRODUCT', 'Отношение к продукту', 'TYPE', null, '1', null, '0000000000100000000000000', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('2004-07-05 16:56:05', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ACC_PRODUCT#INTERFACE.CLASS#ACC_PRODUCT', '0', null, 'CORE', null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 110|');
INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('ACC_VID_DOP_REQS', 'Счета. Виды дополнительных реквизитов', 'TYPE', null, '1', null, '0100000000100000000000000', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('2012-11-07 14:36:41', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ACC_VID_DOP_REQS#INTERFACE.CLASS#ACC_VID_DOP_REQS', '0', null, null, null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 80|');
INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('ACTION_RESULT', 'Результаты выполнения мероприятий', 'TYPE', null, '1', null, '0000000000100000002100000', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('2013-08-01 09:49:29', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ACTION_RESULT#INTERFACE.CLASS#ACTION_RESULT', '0', 'MONITORING_DO', null, null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 431|');
INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('ACT_TYPE_DOL', 'Кадры. Направления деятельности', 'TYPE', null, '1', null, '0000000000100000000000000', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('2006-12-18 17:04:41', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ACT_TYPE_DOL#INTERFACE.CLASS#ACT_TYPE_DOL', '0', 'PERSONNEL', null, null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 204|');
INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('ADDRESS_TYPE', 'Типы адресов', 'TYPE', null, '1', null, '0000100000100000000000000', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('2002-05-23 16:57:47', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ADDRESS_TYPE#INTERFACE.CLASS#ADDRESS_TYPE', '0', 'ORK', 'CORE', null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 35|');
INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('ADG_STANDIN', 'Stand-In 99.99. Управление переключением', 'TYPE', null, '1', 'START_ST', '011000000010000000110000000', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('2019-09-03 14:13:43', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ADG_STANDIN#INTERFACE.CLASS#ADG_STANDIN', '0', 'Stand-In', null, null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 79|');
INSERT INTO IBS.CLASSES (ID, NAME, ENTITY_ID, PARENT_ID, HAS_INSTANCES, INIT_STATE_ID, SHORT_NAME, AGREGATE, BASE_CLASS_ID, TARGET_CLASS_ID, CRITERIA_ID, DATA_SIZE, DATA_PRECISION, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE, AUTONOMOUS, IS_COLLECTION, MODIFIED, PAD_STYLE, PAD_CHAR, PAD_LENGTH, INTERFACE, KERNEL, PARAM_GROUP, TAG, VALIDATOR_ID, INPUT_MASK, TRIGGER_ID, STORAGE_GROUP, INIT_METHOD_ID, LOB_STORAGE_GROUP, TEMP_TYPE, HAS_TYPE, KEY_ATTR, DATA_PRECISION_MIN, PROPERTIES) VALUES ('ADM_DOCUM_STATE', 'Состояния административных  дел РЦ', 'TYPE', null, '1', null, '0000000000100000000000000', 'STRUCTURE', 'STRUCTURE', null, null, null, null, null, null, null, '1', '0', TO_DATE('2004-06-18 20:36:35', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, 'Z#ADM_DOCUM_STATE#INTERFACE.CLASS#ADM_DOCUM_STATE', '0', 'TRC', null, null, null, null, 'SMALL', null, null, null, '0', null, null, '|Weight 4|');
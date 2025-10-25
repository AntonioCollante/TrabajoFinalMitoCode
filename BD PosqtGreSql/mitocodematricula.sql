--
-- PostgreSQL database dump
--

\restrict dWVgyt7bBDnagehZhRI8pFBNONq5KXXW37n2lEbx2ZAeG5cYjddSRDKnJS0ABiv

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

-- Started on 2025-10-25 18:36:43

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS mitocodematricula;
--
-- TOC entry 5060 (class 1262 OID 16389)
-- Name: mitocodematricula; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE mitocodematricula WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Peru.1252';


\unrestrict dWVgyt7bBDnagehZhRI8pFBNONq5KXXW37n2lEbx2ZAeG5cYjddSRDKnJS0ABiv
\connect mitocodematricula
\restrict dWVgyt7bBDnagehZhRI8pFBNONq5KXXW37n2lEbx2ZAeG5cYjddSRDKnJS0ABiv

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 231 (class 1255 OID 16443)
-- Name: fn_listar_cursos(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.fn_listar_cursos() RETURNS TABLE(id integer, nombre character varying, siglas character varying, estado boolean)
    LANGUAGE sql
    AS $$
    SELECT id, nombre, siglas, estado
    FROM curso
    ORDER BY id;
$$;


--
-- TOC entry 232 (class 1255 OID 16440)
-- Name: fn_listar_estudiantes(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.fn_listar_estudiantes() RETURNS TABLE(id integer, nombre character varying, apellidos character varying, dni character varying, edad integer)
    LANGUAGE sql
    AS $$
    SELECT id, nombre, apellidos, dni, edad
    FROM estudiante
    ORDER BY id;
$$;


--
-- TOC entry 227 (class 1255 OID 16445)
-- Name: fn_obtener_curso(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.fn_obtener_curso(p_id integer) RETURNS TABLE(id integer, nombre character varying, siglas character varying, estado boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM curso c WHERE c.id = p_id) THEN
        RAISE EXCEPTION 'NO SE ENCONTRO CURSO CON ID %', p_id;
    END IF;

    RETURN QUERY
    SELECT c.id, c.nombre, c.siglas, c.estado
    FROM curso c
    WHERE c.id = p_id;
END;
$$;


--
-- TOC entry 246 (class 1255 OID 16444)
-- Name: fn_obtener_estudiante(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.fn_obtener_estudiante(p_id integer) RETURNS TABLE(id integer, nombre character varying, apellidos character varying, dni character varying, edad integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM estudiante e WHERE e.id = p_id) THEN
        RAISE EXCEPTION 'NO SE ENCONTRO ESTUDIANTE CON ID %', p_id;
    END IF;

    RETURN QUERY
	SELECT e.id, e.nombre, e.apellidos, e.dni, e.edad
	FROM estudiante e
	WHERE e.id = p_id;

END;
$$;


--
-- TOC entry 247 (class 1255 OID 24616)
-- Name: fn_registrar_matricula(timestamp without time zone, integer, boolean, character varying, integer[], text[]); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.fn_registrar_matricula(p_fecha_matricula timestamp without time zone, p_estudiante_id integer, p_estado boolean, p_usuario_registro character varying, p_cursos integer[], p_aulas text[]) RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE
    v_matricula_id INTEGER;
    i INTEGER;
BEGIN
    IF array_length(p_cursos, 1) IS DISTINCT FROM array_length(p_aulas, 1) THEN
        RAISE EXCEPTION 'El número de cursos no coincide con el número de aulas';
    END IF;

    INSERT INTO matricula (
        fecha_matricula,
        estudiante_id,
        estado,
        fecha_registro,
        usuario_registro
    ) VALUES (
        p_fecha_matricula,
        p_estudiante_id,
        p_estado,
        CURRENT_TIMESTAMP,
        p_usuario_registro
    ) RETURNING id INTO v_matricula_id;

    FOR i IN 1..array_length(p_cursos, 1) LOOP
        INSERT INTO detalle_matricula (
            matricula_id,
            curso_id,
            aula
        ) VALUES (
            v_matricula_id,
            p_cursos[i],
            p_aulas[i]
        );
    END LOOP;
END;
$$;


--
-- TOC entry 229 (class 1255 OID 16441)
-- Name: sp_guardar_curso(character varying, character varying, boolean); Type: PROCEDURE; Schema: public; Owner: -
--

CREATE PROCEDURE public.sp_guardar_curso(IN p_nombre character varying, IN p_siglas character varying, IN p_estado boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF p_nombre IS NULL OR p_siglas IS NULL OR p_estado IS NULL THEN
        RAISE EXCEPTION 'TODOS LOS DATOS DEL CURSO SON OBLIGATORIOS';
    END IF;

    IF LENGTH(p_nombre) > 100 OR LENGTH(p_siglas) > 20 THEN
        RAISE EXCEPTION 'ERROR EN LA LONGITUD DE CAMPOS';
    END IF;

    IF EXISTS (SELECT 1 FROM curso WHERE siglas = p_siglas) THEN
        RAISE EXCEPTION 'LAS SIGLAS YA FUERON ASIGNADAS A OTRO CURSO';
    END IF;

    INSERT INTO curso(nombre, siglas, estado)
    VALUES (p_nombre, p_siglas, p_estado);
END;
$$;


--
-- TOC entry 244 (class 1255 OID 16446)
-- Name: sp_guardar_estudiante(character varying, character varying, character varying, integer); Type: PROCEDURE; Schema: public; Owner: -
--

CREATE PROCEDURE public.sp_guardar_estudiante(IN p_nombre character varying, IN p_apellidos character varying, IN p_dni character varying, IN p_edad integer)
    LANGUAGE plpgsql
    AS $$
BEGIN

    IF p_nombre IS NULL OR p_apellidos IS NULL OR p_dni IS NULL OR p_edad IS NULL THEN
        RAISE EXCEPTION 'TODOIS LOS CAMPOS SON OBLIGATORIOS PARA REGISTRAR AL ESTUDIANTE';
    END IF;

    IF LENGTH(p_nombre) > 100 OR LENGTH(p_apellidos) > 100 OR LENGTH(p_dni) > 20 THEN
        RAISE EXCEPTION 'ERROR EN LA LONGITUD DE CAMPOS';
    END IF;

    IF EXISTS (
		SELECT 1 FROM estudiante WHERE dni = p_dni
	) THEN
        RAISE EXCEPTION 'EL DNI YA SE ENCUENTRA REGISTRADO PARA OTRO ESTUDIANTE';
    END IF;

    INSERT INTO estudiante(nombre, apellidos, dni, edad)
    VALUES (p_nombre, p_apellidos, p_dni, p_edad);
END;
$$;


--
-- TOC entry 237 (class 1255 OID 16438)
-- Name: sp_guardar_estudiante(integer, character varying, character varying, character varying, integer); Type: PROCEDURE; Schema: public; Owner: -
--

CREATE PROCEDURE public.sp_guardar_estudiante(IN p_id integer, IN p_nombre character varying, IN p_apellidos character varying, IN p_dni character varying, IN p_edad integer)
    LANGUAGE plpgsql
    AS $$
BEGIN

    IF p_nombre IS NULL OR p_apellidos IS NULL OR p_dni IS NULL OR p_edad IS NULL THEN
        RAISE EXCEPTION 'TODOIS LOS CAMPOS SON OBLIGATORIOS PARA REGISTRAR AL ESTUDIANTE';
    END IF;

    IF LENGTH(p_nombre) > 100 OR LENGTH(p_apellidos) > 100 OR LENGTH(p_dni) > 20 THEN
        RAISE EXCEPTION 'ERROR EN LA LONGITUD DE CAMPOS';
    END IF;

    IF EXISTS (
		SELECT 1 FROM estudiante WHERE dni = p_dni
	) THEN
        RAISE EXCEPTION 'EL DNI YA SE ENCUENTRA REGISTRADO PARA OTRO ESTUDIANTE';
    END IF;

    INSERT INTO estudiante(nombre, apellidos, dni, edad)
    VALUES (p_nombre, p_apellidos, p_dni, p_edad);
END;
$$;


--
-- TOC entry 230 (class 1255 OID 16442)
-- Name: sp_modificar_curso(integer, character varying, character varying, boolean); Type: PROCEDURE; Schema: public; Owner: -
--

CREATE PROCEDURE public.sp_modificar_curso(IN p_id integer, IN p_nombre character varying, IN p_siglas character varying, IN p_estado boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM curso WHERE id = p_id) THEN
        RAISE EXCEPTION 'NO SE ENCONTRO INFORMACION DEL CURSO';
    END IF;

    IF LENGTH(p_nombre) > 100 OR LENGTH(p_siglas) > 20 THEN
        RAISE EXCEPTION 'ERROR EN LA LONGITUD DE CAMPOS';
    END IF;

    IF EXISTS (
        SELECT 1 FROM curso WHERE siglas = p_siglas AND id <> p_id
    ) THEN
        RAISE EXCEPTION 'LAS SIGLAS YA FUERON ASIGNADAS A OTRO CURSO';
    END IF;

    UPDATE curso
    SET nombre = p_nombre,
        siglas = p_siglas,
        estado = p_estado
    WHERE id = p_id;
END;
$$;


--
-- TOC entry 228 (class 1255 OID 16439)
-- Name: sp_modificar_estudiante(integer, character varying, character varying, character varying, integer); Type: PROCEDURE; Schema: public; Owner: -
--

CREATE PROCEDURE public.sp_modificar_estudiante(IN p_id integer, IN p_nombre character varying, IN p_apellidos character varying, IN p_dni character varying, IN p_edad integer)
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM estudiante WHERE id = p_id) THEN
        RAISE EXCEPTION 'NO EXISTE INFORMACION DEL ESTUDIANTE';
    END IF;

    IF LENGTH(p_nombre) > 100 OR LENGTH(p_apellidos) > 100 OR LENGTH(p_dni) > 20 THEN
        RAISE EXCEPTION 'ERROR EN LA LONGITUD DE CAMPOS';
    END IF;

    IF EXISTS (
        SELECT 1 FROM estudiante WHERE dni = p_dni AND id <> p_id
    ) THEN
        RAISE EXCEPTION 'EL DNI YA SE ENCUENTRA REGISTRADO PARA OTRO ESTUDIANTE';
    END IF;

    UPDATE estudiante
    SET nombre = p_nombre,
        apellidos = p_apellidos,
        dni = p_dni,
        edad = p_edad
    WHERE id = p_id;
END;
$$;


--
-- TOC entry 222 (class 1259 OID 16427)
-- Name: curso; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.curso (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    siglas character varying(20) NOT NULL,
    estado boolean NOT NULL
);


--
-- TOC entry 221 (class 1259 OID 16426)
-- Name: curso_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.curso ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.curso_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 226 (class 1259 OID 24595)
-- Name: detalle_matricula; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.detalle_matricula (
    id integer NOT NULL,
    matricula_id integer NOT NULL,
    curso_id integer NOT NULL,
    aula character varying(50) NOT NULL
);


--
-- TOC entry 225 (class 1259 OID 24594)
-- Name: detalle_matricula_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.detalle_matricula_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 5061 (class 0 OID 0)
-- Dependencies: 225
-- Name: detalle_matricula_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.detalle_matricula_id_seq OWNED BY public.detalle_matricula.id;


--
-- TOC entry 220 (class 1259 OID 16414)
-- Name: estudiante; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.estudiante (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    apellidos character varying(100) NOT NULL,
    dni character varying(20) NOT NULL,
    edad integer NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 16413)
-- Name: estudiante_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.estudiante ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.estudiante_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 224 (class 1259 OID 24577)
-- Name: matricula; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.matricula (
    id integer NOT NULL,
    fecha_matricula timestamp without time zone NOT NULL,
    estudiante_id integer NOT NULL,
    estado boolean DEFAULT true NOT NULL,
    fecha_registro timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    usuario_registro character varying(50)
);


--
-- TOC entry 223 (class 1259 OID 24576)
-- Name: matricula_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.matricula_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 5062 (class 0 OID 0)
-- Dependencies: 223
-- Name: matricula_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.matricula_id_seq OWNED BY public.matricula.id;


--
-- TOC entry 4884 (class 2604 OID 24598)
-- Name: detalle_matricula id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.detalle_matricula ALTER COLUMN id SET DEFAULT nextval('public.detalle_matricula_id_seq'::regclass);


--
-- TOC entry 4881 (class 2604 OID 24580)
-- Name: matricula id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.matricula ALTER COLUMN id SET DEFAULT nextval('public.matricula_id_seq'::regclass);


--
-- TOC entry 5050 (class 0 OID 16427)
-- Dependencies: 222
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.curso (id, nombre, siglas, estado) FROM stdin;
1	Matemática Básica	MATBAS	t
2	Física General	FISGEN	t
3	Química Orgánica	QUIORG	t
4	Programación I	PROG1	t
5	Programación II	PROG2	t
6	Algoritmos y Estructuras	ALGEST	t
7	Bases de Datos	BBDD	t
8	Sistemas Operativos	SISOPE	t
9	Redes de Computadoras	REDCOM	t
10	Ingeniería de Software	INSOFT	t
11	Gestión de Proyectos	GESPRO	t
12	Contabilidad Financiera	CONFIN	f
13	Derecho Empresarial	DEREMP	f
14	Marketing Digital	MARKDI	t
15	Inteligencia Artificial	IA2025	t
16	AMAZOM WEB SERVICES	AWS	t
\.


--
-- TOC entry 5054 (class 0 OID 24595)
-- Dependencies: 226
-- Data for Name: detalle_matricula; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.detalle_matricula (id, matricula_id, curso_id, aula) FROM stdin;
1	1	4	Aula 202
2	1	7	Laboratorio 3
\.


--
-- TOC entry 5048 (class 0 OID 16414)
-- Dependencies: 220
-- Data for Name: estudiante; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.estudiante (id, nombre, apellidos, dni, edad) FROM stdin;
1	Lucía	Ramírez Soto	72145678	22
2	Carlos	Mendoza Ruiz	74891234	24
3	Valeria	Torres Paredes	73218945	21
4	Jorge	Fernández Quispe	76543210	25
5	Ana	Gómez Chávez	70123456	23
6	Diego	Salazar Rojas	78901234	22
7	María	Paredes León	71234567	26
8	Luis	Quispe Vargas	75432109	23
9	Camila	Rojas Delgado	79876543	21
10	Andrés	Vargas Peña	74321098	24
11	Juan Antonio	Collantes Meía	43457638	37
\.


--
-- TOC entry 5052 (class 0 OID 24577)
-- Dependencies: 224
-- Data for Name: matricula; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.matricula (id, fecha_matricula, estudiante_id, estado, fecha_registro, usuario_registro) FROM stdin;
1	2025-10-25 15:30:00	1	t	2025-10-25 17:34:31.851069	WebService
\.


--
-- TOC entry 5063 (class 0 OID 0)
-- Dependencies: 221
-- Name: curso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.curso_id_seq', 16, true);


--
-- TOC entry 5064 (class 0 OID 0)
-- Dependencies: 225
-- Name: detalle_matricula_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.detalle_matricula_id_seq', 2, true);


--
-- TOC entry 5065 (class 0 OID 0)
-- Dependencies: 219
-- Name: estudiante_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.estudiante_id_seq', 11, true);


--
-- TOC entry 5066 (class 0 OID 0)
-- Dependencies: 223
-- Name: matricula_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.matricula_id_seq', 1, true);


--
-- TOC entry 4890 (class 2606 OID 16435)
-- Name: curso curso_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_pkey PRIMARY KEY (id);


--
-- TOC entry 4892 (class 2606 OID 16437)
-- Name: curso curso_siglas_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_siglas_key UNIQUE (siglas);


--
-- TOC entry 4896 (class 2606 OID 24604)
-- Name: detalle_matricula detalle_matricula_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.detalle_matricula
    ADD CONSTRAINT detalle_matricula_pkey PRIMARY KEY (id);


--
-- TOC entry 4886 (class 2606 OID 16425)
-- Name: estudiante estudiante_dni_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_dni_key UNIQUE (dni);


--
-- TOC entry 4888 (class 2606 OID 16423)
-- Name: estudiante estudiante_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (id);


--
-- TOC entry 4894 (class 2606 OID 24588)
-- Name: matricula matricula_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT matricula_pkey PRIMARY KEY (id);


--
-- TOC entry 4898 (class 2606 OID 24610)
-- Name: detalle_matricula fk_curso; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.detalle_matricula
    ADD CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES public.curso(id);


--
-- TOC entry 4897 (class 2606 OID 24589)
-- Name: matricula fk_estudiante; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.matricula
    ADD CONSTRAINT fk_estudiante FOREIGN KEY (estudiante_id) REFERENCES public.estudiante(id);


--
-- TOC entry 4899 (class 2606 OID 24605)
-- Name: detalle_matricula fk_matricula; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.detalle_matricula
    ADD CONSTRAINT fk_matricula FOREIGN KEY (matricula_id) REFERENCES public.matricula(id);


-- Completed on 2025-10-25 18:36:43

--
-- PostgreSQL database dump complete
--

\unrestrict dWVgyt7bBDnagehZhRI8pFBNONq5KXXW37n2lEbx2ZAeG5cYjddSRDKnJS0ABiv


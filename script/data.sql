--
-- PostgreSQL database dump
--

-- Dumped from database version 10.22
-- Dumped by pg_dump version 10.22

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: besoin_secteur; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.besoin_secteur (
    id_besoin integer NOT NULL,
    daty date,
    heure_coupure time without time zone,
    nombre_personne_matin integer,
    nombre_personne_apres_midi integer,
    puissance_moyenne double precision,
    id_secteur character varying
);


ALTER TABLE public.besoin_secteur OWNER TO mamisoa;

--
-- Name: besoin_secteur_id_besoin_seq; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.besoin_secteur_id_besoin_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.besoin_secteur_id_besoin_seq OWNER TO mamisoa;

--
-- Name: besoin_secteur_id_besoin_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mamisoa
--

ALTER SEQUENCE public.besoin_secteur_id_besoin_seq OWNED BY public.besoin_secteur.id_besoin;


--
-- Name: coupure; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.coupure (
    id character varying(50) NOT NULL,
    date_jour date,
    id_secteur character varying(50),
    heure time without time zone
);


ALTER TABLE public.coupure OWNER TO mamisoa;

--
-- Name: details; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.details (
    id integer NOT NULL,
    date_details date,
    heure time without time zone,
    secteur character varying(50) NOT NULL,
    puissance_delivree double precision,
    puissance_delivree_batterie double precision,
    reserve_batterie double precision,
    besoin double precision,
    etat character varying(50)
);


ALTER TABLE public.details OWNER TO mamisoa;

--
-- Name: details_id_seq; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.details_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.details_id_seq OWNER TO mamisoa;

--
-- Name: details_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mamisoa
--

ALTER SEQUENCE public.details_id_seq OWNED BY public.details.id;


--
-- Name: meteo; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.meteo (
    id character varying(50) NOT NULL,
    date_meteo date,
    heure_debut time without time zone,
    heure_fin time without time zone,
    luminosite double precision
);


ALTER TABLE public.meteo OWNER TO mamisoa;

--
-- Name: pointage; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.pointage (
    id character varying(50) NOT NULL,
    id_salle character varying,
    daty date,
    heure_debut time without time zone,
    heure_fin time without time zone,
    nombre_personne integer
);


ALTER TABLE public.pointage OWNER TO mamisoa;

--
-- Name: salle; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.salle (
    id_salle character varying(50) NOT NULL,
    nom character varying(50) NOT NULL,
    capacite_max integer NOT NULL,
    id_secteur character varying(50) NOT NULL
);


ALTER TABLE public.salle OWNER TO mamisoa;

--
-- Name: secteur; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.secteur (
    id_secteur character varying(50) NOT NULL,
    nom character varying(50) NOT NULL
);


ALTER TABLE public.secteur OWNER TO mamisoa;

--
-- Name: seq_coupure; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_coupure
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_coupure OWNER TO mamisoa;

--
-- Name: seq_meteo; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_meteo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_meteo OWNER TO mamisoa;

--
-- Name: seq_pointage; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_pointage
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_pointage OWNER TO mamisoa;

--
-- Name: seq_salle; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_salle
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_salle OWNER TO mamisoa;

--
-- Name: seq_secteur; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_secteur
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_secteur OWNER TO mamisoa;

--
-- Name: seq_source; Type: SEQUENCE; Schema: public; Owner: mamisoa
--

CREATE SEQUENCE public.seq_source
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_source OWNER TO mamisoa;

--
-- Name: source_solaire; Type: TABLE; Schema: public; Owner: mamisoa
--

CREATE TABLE public.source_solaire (
    id_source character varying(50) NOT NULL,
    puissance_max double precision NOT NULL,
    reserve_max_batterie double precision NOT NULL,
    id_secteur character varying(50)
);


ALTER TABLE public.source_solaire OWNER TO mamisoa;

--
-- Name: v_pointage_secteur; Type: VIEW; Schema: public; Owner: mamisoa
--

CREATE VIEW public.v_pointage_secteur AS
SELECT
    NULL::character varying(50) AS id_secteur,
    NULL::character varying(50) AS nom,
    NULL::date AS daty,
    NULL::time without time zone AS heure_debut,
    NULL::time without time zone AS heure_fin,
    NULL::bigint AS nombre_personne;


ALTER TABLE public.v_pointage_secteur OWNER TO mamisoa;

--
-- Name: besoin_secteur id_besoin; Type: DEFAULT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.besoin_secteur ALTER COLUMN id_besoin SET DEFAULT nextval('public.besoin_secteur_id_besoin_seq'::regclass);


--
-- Name: details id; Type: DEFAULT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.details ALTER COLUMN id SET DEFAULT nextval('public.details_id_seq'::regclass);


--
-- Data for Name: besoin_secteur; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.besoin_secteur (id_besoin, daty, heure_coupure, nombre_personne_matin, nombre_personne_apres_midi, puissance_moyenne, id_secteur) FROM stdin;
\.


--
-- Data for Name: coupure; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.coupure (id, date_jour, id_secteur, heure) FROM stdin;
COUP001	2023-11-01	SEC001	15:45:00
COUP002	2023-11-02	SEC001	16:30:00
COUP003	2023-11-03	SEC001	17:00:00
COUP004	2023-11-06	SEC001	15:00:00
COUP005	2023-11-07	SEC001	15:40:00
COUP006	2023-11-08	SEC001	15:45:00
COUP007	2023-11-09	SEC001	16:29:00
COUP008	2023-11-10	SEC001	17:01:00
COUP009	2023-11-13	SEC001	15:02:00
COUP010	2023-11-14	SEC001	15:42:00
COUP011	2023-11-15	SEC001	15:43:00
COUP012	2023-11-16	SEC001	16:32:00
COUP013	2023-11-17	SEC001	17:03:00
COUP014	2023-11-20	SEC001	14:55:00
COUP015	2023-11-21	SEC001	15:40:00
COUP016	2023-11-22	SEC001	16:25:00
COUP017	2023-11-23	SEC001	16:54:00
COUP018	2023-11-24	SEC001	14:55:00
COUP019	2023-11-27	SEC001	16:41:00
COUP020	2023-11-28	SEC001	17:13:00
COUP021	2023-11-29	SEC001	15:03:00
COUP022	2023-11-30	SEC001	15:49:00
COUP023	2023-12-01	SEC001	16:34:00
COUP024	2023-12-04	SEC001	16:49:00
COUP025	2023-12-05	SEC001	14:51:00
COUP026	2023-12-06	SEC001	16:36:00
COUP027	2023-12-07	SEC001	17:08:00
COUP028	2023-12-08	SEC001	14:59:00
COUP029	2023-12-11	SEC001	15:46:00
COUP030	2023-12-12	SEC001	16:32:00
COUP031	2023-12-13	SEC001	17:01:00
\.


--
-- Data for Name: details; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.details (id, date_details, heure, secteur, puissance_delivree, puissance_delivree_batterie, reserve_batterie, besoin, etat) FROM stdin;
\.


--
-- Data for Name: meteo; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.meteo (id, date_meteo, heure_debut, heure_fin, luminosite) FROM stdin;
MET0001	2023-11-20	08:00:00	08:59:00	8
MET0002	2023-11-20	09:00:00	09:59:00	7
MET0003	2023-11-20	10:00:00	10:59:00	9
MET0004	2023-11-20	11:00:00	11:59:00	9
MET0005	2023-11-20	12:00:00	12:59:00	10
MET0006	2023-11-20	01:00:00	01:59:00	8
MET0007	2023-11-20	02:00:00	02:59:00	8
MET0008	2023-11-20	03:00:00	03:59:00	7
MET0009	2023-11-20	04:00:00	04:59:00	6
MET0010	2023-11-20	05:00:00	06:00:00	4
MET0011	2023-11-21	08:00:00	08:59:00	8
MET0012	2023-11-21	09:00:00	09:59:00	7
MET0013	2023-11-21	10:00:00	10:59:00	9
MET0014	2023-11-21	11:00:00	11:59:00	9
MET0015	2023-11-21	12:00:00	12:59:00	10
MET0016	2023-11-21	01:00:00	01:59:00	8
MET0017	2023-11-21	02:00:00	02:59:00	8
MET0018	2023-11-21	03:00:00	03:59:00	7
MET0019	2023-11-21	04:00:00	04:59:00	6
MET0020	2023-11-21	05:00:00	06:00:00	4
MET0021	2023-11-22	08:00:00	08:59:00	8
MET0022	2023-11-22	09:00:00	09:59:00	7
MET0023	2023-11-22	10:00:00	10:59:00	9
MET0024	2023-11-22	11:00:00	11:59:00	9
MET0025	2023-11-22	12:00:00	12:59:00	10
MET0026	2023-11-22	01:00:00	01:59:00	8
MET0027	2023-11-22	02:00:00	02:59:00	8
MET0028	2023-11-22	03:00:00	03:59:00	7
MET0029	2023-11-22	04:00:00	04:59:00	6
MET0030	2023-11-22	05:00:00	06:00:00	4
MET0031	2023-11-22	08:00:00	08:59:00	8
MET0032	2023-11-22	09:00:00	09:59:00	7
MET0033	2023-11-22	10:00:00	10:59:00	9
MET0034	2023-11-22	11:00:00	11:59:00	9
MET0035	2023-11-22	12:00:00	12:59:00	10
MET0036	2023-11-22	01:00:00	01:59:00	8
MET0037	2023-11-22	02:00:00	02:59:00	8
MET0038	2023-11-22	03:00:00	03:59:00	7
MET0039	2023-11-22	04:00:00	04:59:00	6
MET0040	2023-11-22	05:00:00	06:00:00	4
MET0041	2023-11-23	08:00:00	08:59:00	8
MET0042	2023-11-23	09:00:00	09:59:00	7
MET0043	2023-11-23	10:00:00	10:59:00	9
MET0044	2023-11-23	11:00:00	11:59:00	9
MET0045	2023-11-23	12:00:00	12:59:00	10
MET0046	2023-11-23	01:00:00	01:59:00	8
MET0047	2023-11-23	02:00:00	02:59:00	8
MET0048	2023-11-23	03:00:00	03:59:00	7
MET0049	2023-11-23	04:00:00	04:59:00	6
MET0050	2023-11-23	05:00:00	06:00:00	4
MET0051	2023-11-24	09:00:00	09:59:00	7
MET0052	2023-11-24	10:00:00	10:59:00	9
MET0053	2023-11-24	11:00:00	11:59:00	9
MET0054	2023-11-24	12:00:00	12:59:00	10
MET0055	2023-11-24	01:00:00	01:59:00	8
MET0056	2023-11-24	02:00:00	02:59:00	8
MET0057	2023-11-24	03:00:00	03:59:00	7
MET0058	2023-11-24	04:00:00	04:59:00	6
MET0059	2023-11-24	05:00:00	06:00:00	4
MET0060	2023-11-27	09:00:00	09:59:00	7
MET0061	2023-11-27	10:00:00	10:59:00	9
MET0062	2023-11-27	11:00:00	11:59:00	9
MET0063	2023-11-27	12:00:00	12:59:00	10
MET0064	2023-11-27	01:00:00	01:59:00	8
MET0065	2023-11-27	02:00:00	02:59:00	8
MET0066	2023-11-27	03:00:00	03:59:00	7
MET0067	2023-11-27	04:00:00	04:59:00	6
MET0068	2023-11-27	05:00:00	06:00:00	4
MET0069	2023-11-28	09:00:00	09:59:00	7
MET0070	2023-11-28	10:00:00	10:59:00	9
MET0071	2023-11-28	11:00:00	11:59:00	9
MET0072	2023-11-28	12:00:00	12:59:00	10
MET0073	2023-11-28	01:00:00	01:59:00	8
MET0074	2023-11-28	02:00:00	02:59:00	8
MET0075	2023-11-28	03:00:00	03:59:00	7
MET0076	2023-11-28	04:00:00	04:59:00	6
MET0077	2023-11-28	05:00:00	06:00:00	4
MET0078	2023-11-29	09:00:00	09:59:00	7
MET0079	2023-11-29	10:00:00	10:59:00	9
MET0080	2023-11-29	11:00:00	11:59:00	9
MET0081	2023-11-29	12:00:00	12:59:00	10
MET0082	2023-11-29	01:00:00	01:59:00	8
MET0083	2023-11-29	02:00:00	02:59:00	8
MET0084	2023-11-29	03:00:00	03:59:00	7
MET0085	2023-11-29	04:00:00	04:59:00	6
MET0086	2023-11-29	05:00:00	06:00:00	4
MET0087	2023-11-30	09:00:00	09:59:00	7
MET0088	2023-11-30	10:00:00	10:59:00	9
MET0089	2023-11-30	11:00:00	11:59:00	9
MET0090	2023-11-30	12:00:00	12:59:00	10
MET0091	2023-11-30	01:00:00	01:59:00	8
MET0092	2023-11-30	02:00:00	02:59:00	8
MET0093	2023-11-30	03:00:00	03:59:00	7
MET0094	2023-11-30	04:00:00	04:59:00	6
MET0095	2023-11-30	05:00:00	06:00:00	4
MET0096	2023-12-01	09:00:00	09:59:00	7
MET0097	2023-12-01	10:00:00	10:59:00	9
MET0098	2023-12-01	11:00:00	11:59:00	9
MET0099	2023-12-01	12:00:00	12:59:00	10
MET0100	2023-12-01	01:00:00	01:59:00	8
MET0101	2023-12-01	02:00:00	02:59:00	8
MET0102	2023-12-01	03:00:00	03:59:00	7
MET0103	2023-12-01	04:00:00	04:59:00	6
MET0104	2023-12-01	05:00:00	06:00:00	4
MET0105	2023-12-04	09:00:00	09:59:00	7
MET0106	2023-12-04	10:00:00	10:59:00	9
MET0107	2023-12-04	11:00:00	11:59:00	9
MET0108	2023-12-04	12:00:00	12:59:00	10
MET0109	2023-12-04	01:00:00	01:59:00	8
MET0110	2023-12-04	02:00:00	02:59:00	8
MET0111	2023-12-04	03:00:00	03:59:00	7
MET0112	2023-12-04	04:00:00	04:59:00	6
MET0113	2023-12-04	05:00:00	06:00:00	4
MET0114	2023-12-05	09:00:00	09:59:00	7
MET0115	2023-12-05	10:00:00	10:59:00	9
MET0116	2023-12-05	11:00:00	11:59:00	9
MET0117	2023-12-05	12:00:00	12:59:00	10
MET0118	2023-12-05	01:00:00	01:59:00	8
MET0119	2023-12-05	02:00:00	02:59:00	8
MET0120	2023-12-05	03:00:00	03:59:00	7
MET0121	2023-12-05	04:00:00	04:59:00	6
MET0122	2023-12-05	05:00:00	06:00:00	4
MET0123	2023-12-06	09:00:00	09:59:00	7
MET0124	2023-12-06	10:00:00	10:59:00	9
MET0125	2023-12-06	11:00:00	11:59:00	9
MET0126	2023-12-06	12:00:00	12:59:00	10
MET0127	2023-12-06	01:00:00	01:59:00	8
MET0128	2023-12-06	02:00:00	02:59:00	8
MET0129	2023-12-06	03:00:00	03:59:00	7
MET0130	2023-12-06	04:00:00	04:59:00	6
MET0131	2023-12-06	05:00:00	06:00:00	4
MET0132	2023-12-07	09:00:00	09:59:00	7
MET0133	2023-12-07	10:00:00	10:59:00	9
MET0134	2023-12-07	11:00:00	11:59:00	9
MET0135	2023-12-07	12:00:00	12:59:00	10
MET0136	2023-12-07	01:00:00	01:59:00	8
MET0137	2023-12-07	02:00:00	02:59:00	8
MET0138	2023-12-07	03:00:00	03:59:00	7
MET0139	2023-12-07	04:00:00	04:59:00	6
MET0140	2023-12-07	05:00:00	06:00:00	4
MET0141	2023-12-08	09:00:00	09:59:00	7
MET0142	2023-12-08	10:00:00	10:59:00	9
MET0143	2023-12-08	11:00:00	11:59:00	9
MET0144	2023-12-08	12:00:00	12:59:00	10
MET0145	2023-12-08	01:00:00	01:59:00	8
MET0146	2023-12-08	02:00:00	02:59:00	8
MET0147	2023-12-08	03:00:00	03:59:00	7
MET0148	2023-12-08	04:00:00	04:59:00	6
MET0149	2023-12-08	05:00:00	06:00:00	4
MET0150	2023-12-11	09:00:00	09:59:00	7
MET0151	2023-12-11	10:00:00	10:59:00	9
MET0152	2023-12-11	11:00:00	11:59:00	9
MET0153	2023-12-11	12:00:00	12:59:00	10
MET0154	2023-12-11	01:00:00	01:59:00	8
MET0155	2023-12-11	02:00:00	02:59:00	8
MET0156	2023-12-11	03:00:00	03:59:00	7
MET0157	2023-12-11	04:00:00	04:59:00	6
MET0158	2023-12-11	05:00:00	06:00:00	4
MET0159	2023-12-12	09:00:00	09:59:00	7
MET0160	2023-12-12	10:00:00	10:59:00	9
MET0161	2023-12-12	11:00:00	11:59:00	9
MET0162	2023-12-12	12:00:00	12:59:00	10
MET0163	2023-12-12	01:00:00	01:59:00	8
MET0164	2023-12-12	02:00:00	02:59:00	8
MET0165	2023-12-12	03:00:00	03:59:00	7
MET0166	2023-12-12	04:00:00	04:59:00	6
MET0167	2023-12-12	05:00:00	06:00:00	4
MET0168	2023-12-13	09:00:00	09:59:00	7
MET0169	2023-12-13	10:00:00	10:59:00	9
MET0170	2023-12-13	11:00:00	11:59:00	9
MET0171	2023-12-13	12:00:00	12:59:00	10
MET0172	2023-12-13	01:00:00	01:59:00	8
MET0173	2023-12-13	02:00:00	02:59:00	8
MET0174	2023-12-13	03:00:00	03:59:00	7
MET0175	2023-12-13	04:00:00	04:59:00	6
MET0176	2023-12-13	05:00:00	06:00:00	4
MET0177	2023-11-01	08:00:00	08:59:00	8
MET0178	2023-11-01	09:00:00	09:59:00	7
MET0179	2023-11-01	10:00:00	10:59:00	9
MET0180	2023-11-01	11:00:00	11:59:00	9
MET0181	2023-11-01	12:00:00	12:59:00	10
MET0182	2023-11-01	01:00:00	01:59:00	8
MET0183	2023-11-01	02:00:00	02:59:00	8
MET0184	2023-11-01	03:00:00	03:59:00	7
MET0185	2023-11-01	04:00:00	04:59:00	6
MET0186	2023-11-01	05:00:00	06:00:00	4
MET0187	2023-11-02	08:00:00	08:59:00	8
MET0188	2023-11-02	09:00:00	09:59:00	7
MET0189	2023-11-02	10:00:00	10:59:00	9
MET0190	2023-11-02	11:00:00	11:59:00	9
MET0191	2023-11-02	12:00:00	12:59:00	10
MET0192	2023-11-02	01:00:00	01:59:00	8
MET0193	2023-11-02	02:00:00	02:59:00	8
MET0194	2023-11-02	03:00:00	03:59:00	7
MET0195	2023-11-02	04:00:00	04:59:00	6
MET0196	2023-11-02	05:00:00	06:00:00	4
MET0197	2023-11-03	08:00:00	08:59:00	8
MET0198	2023-11-03	09:00:00	09:59:00	7
MET0199	2023-11-03	10:00:00	10:59:00	9
MET0200	2023-11-03	11:00:00	11:59:00	9
MET0201	2023-11-03	12:00:00	12:59:00	10
MET0202	2023-11-03	01:00:00	01:59:00	8
MET0203	2023-11-03	02:00:00	02:59:00	8
MET0204	2023-11-03	03:00:00	03:59:00	7
MET0205	2023-11-03	04:00:00	04:59:00	6
MET0206	2023-11-03	05:00:00	06:00:00	4
MET0207	2023-11-06	08:00:00	08:59:00	8
MET0208	2023-11-06	09:00:00	09:59:00	7
MET0209	2023-11-06	10:00:00	10:59:00	9
MET0210	2023-11-06	11:00:00	11:59:00	9
MET0211	2023-11-06	12:00:00	12:59:00	10
MET0212	2023-11-06	01:00:00	01:59:00	8
MET0213	2023-11-06	02:00:00	02:59:00	8
MET0214	2023-11-06	03:00:00	03:59:00	7
MET0215	2023-11-06	04:00:00	04:59:00	6
MET0216	2023-11-06	05:00:00	06:00:00	4
MET0217	2023-11-07	08:00:00	08:59:00	8
MET0218	2023-11-07	09:00:00	09:59:00	7
MET0219	2023-11-07	10:00:00	10:59:00	9
MET0220	2023-11-07	11:00:00	11:59:00	9
MET0221	2023-11-07	12:00:00	12:59:00	10
MET0222	2023-11-07	01:00:00	01:59:00	8
MET0223	2023-11-07	02:00:00	02:59:00	8
MET0224	2023-11-07	03:00:00	03:59:00	7
MET0225	2023-11-07	04:00:00	04:59:00	6
MET0226	2023-11-07	05:00:00	06:00:00	4
MET0227	2023-11-08	08:00:00	08:59:00	8
MET0228	2023-11-08	09:00:00	09:59:00	7
MET0229	2023-11-08	10:00:00	10:59:00	9
MET0230	2023-11-08	11:00:00	11:59:00	9
MET0231	2023-11-08	12:00:00	12:59:00	10
MET0232	2023-11-08	01:00:00	01:59:00	8
MET0233	2023-11-08	02:00:00	02:59:00	8
MET0234	2023-11-08	03:00:00	03:59:00	7
MET0235	2023-11-08	04:00:00	04:59:00	6
MET0236	2023-11-08	05:00:00	06:00:00	4
MET0237	2023-11-09	08:00:00	08:59:00	8
MET0238	2023-11-09	09:00:00	09:59:00	7
MET0239	2023-11-09	10:00:00	10:59:00	9
MET0240	2023-11-09	11:00:00	11:59:00	9
MET0241	2023-11-09	12:00:00	12:59:00	10
MET0242	2023-11-09	01:00:00	01:59:00	8
MET0243	2023-11-09	02:00:00	02:59:00	8
MET0244	2023-11-09	03:00:00	03:59:00	7
MET0245	2023-11-09	04:00:00	04:59:00	6
MET0246	2023-11-09	05:00:00	06:00:00	4
MET0247	2023-11-10	08:00:00	08:59:00	8
MET0248	2023-11-10	09:00:00	09:59:00	7
MET0249	2023-11-10	10:00:00	10:59:00	9
MET0250	2023-11-10	11:00:00	11:59:00	9
MET0251	2023-11-10	12:00:00	12:59:00	10
MET0252	2023-11-10	01:00:00	01:59:00	8
MET0253	2023-11-10	02:00:00	02:59:00	8
MET0254	2023-11-10	03:00:00	03:59:00	7
MET0255	2023-11-10	04:00:00	04:59:00	6
MET0256	2023-11-10	05:00:00	06:00:00	4
MET0257	2023-11-13	08:00:00	08:59:00	8
MET0258	2023-11-13	09:00:00	09:59:00	7
MET0259	2023-11-13	10:00:00	10:59:00	9
MET0260	2023-11-13	11:00:00	11:59:00	9
MET0261	2023-11-13	12:00:00	12:59:00	10
MET0262	2023-11-13	01:00:00	01:59:00	8
MET0263	2023-11-13	02:00:00	02:59:00	8
MET0264	2023-11-13	03:00:00	03:59:00	7
MET0265	2023-11-13	04:00:00	04:59:00	6
MET0266	2023-11-13	05:00:00	06:00:00	4
MET0267	2023-11-14	08:00:00	08:59:00	8
MET0268	2023-11-14	09:00:00	09:59:00	7
MET0269	2023-11-14	10:00:00	10:59:00	9
MET0270	2023-11-14	11:00:00	11:59:00	9
MET0271	2023-11-14	12:00:00	12:59:00	10
MET0272	2023-11-14	01:00:00	01:59:00	8
MET0273	2023-11-14	02:00:00	02:59:00	8
MET0274	2023-11-14	03:00:00	03:59:00	7
MET0275	2023-11-14	04:00:00	04:59:00	6
MET0276	2023-11-14	05:00:00	06:00:00	4
MET0277	2023-11-15	08:00:00	08:59:00	8
MET0278	2023-11-15	09:00:00	09:59:00	7
MET0279	2023-11-15	10:00:00	10:59:00	9
MET0280	2023-11-15	11:00:00	11:59:00	9
MET0281	2023-11-15	12:00:00	12:59:00	10
MET0282	2023-11-15	01:00:00	01:59:00	8
MET0283	2023-11-15	02:00:00	02:59:00	8
MET0284	2023-11-15	03:00:00	03:59:00	7
MET0285	2023-11-15	04:00:00	04:59:00	6
MET0286	2023-11-15	05:00:00	06:00:00	4
MET0287	2023-11-16	08:00:00	08:59:00	8
MET0288	2023-11-16	09:00:00	09:59:00	7
MET0289	2023-11-16	10:00:00	10:59:00	9
MET0290	2023-11-16	11:00:00	11:59:00	9
MET0291	2023-11-16	12:00:00	12:59:00	10
MET0292	2023-11-16	01:00:00	01:59:00	8
MET0293	2023-11-16	02:00:00	02:59:00	8
MET0294	2023-11-16	03:00:00	03:59:00	7
MET0295	2023-11-16	04:00:00	04:59:00	6
MET0296	2023-11-16	05:00:00	06:00:00	4
MET0297	2023-11-17	08:00:00	08:59:00	8
MET0298	2023-11-17	09:00:00	09:59:00	7
MET0299	2023-11-17	10:00:00	10:59:00	9
MET0300	2023-11-17	11:00:00	11:59:00	9
MET0301	2023-11-17	12:00:00	12:59:00	10
MET0302	2023-11-17	01:00:00	01:59:00	8
MET0303	2023-11-17	02:00:00	02:59:00	8
MET0304	2023-11-17	03:00:00	03:59:00	7
MET0305	2023-11-17	04:00:00	04:59:00	6
MET0306	2023-11-17	05:00:00	06:00:00	4
\.


--
-- Data for Name: pointage; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.pointage (id, id_salle, daty, heure_debut, heure_fin, nombre_personne) FROM stdin;
POIN00001	SAL001	2023-11-01	08:00:00	11:59:00	300
POIN00002	SAL001	2023-11-01	12:00:00	17:59:00	280
POIN00003	SAL001	2023-11-02	08:00:00	11:59:00	250
POIN00004	SAL001	2023-11-02	12:00:00	17:59:00	240
POIN00005	SAL001	2023-11-03	08:00:00	11:59:00	220
POIN00006	SAL001	2023-11-03	12:00:00	17:59:00	210
POIN00007	SAL001	2023-11-06	08:00:00	11:59:00	320
POIN00008	SAL001	2023-11-06	12:00:00	17:59:00	330
POIN00009	SAL001	2023-11-07	08:00:00	11:59:00	300
POIN00011	SAL001	2023-11-08	08:00:00	11:59:00	300
POIN00012	SAL001	2023-11-08	12:00:00	17:59:00	280
POIN00013	SAL001	2023-11-09	08:00:00	11:59:00	250
POIN00014	SAL001	2023-11-09	12:00:00	17:59:00	240
POIN00015	SAL001	2023-11-10	08:00:00	11:59:00	220
POIN00016	SAL001	2023-11-10	12:00:00	17:59:00	210
POIN00021	SAL001	2023-11-15	08:00:00	11:59:00	300
POIN00022	SAL001	2023-11-15	12:00:00	17:59:00	280
POIN00023	SAL001	2023-11-16	08:00:00	11:59:00	250
POIN00024	SAL001	2023-11-16	12:00:00	17:59:00	240
POIN00025	SAL001	2023-11-17	08:00:00	11:59:00	220
POIN00026	SAL001	2023-11-17	12:00:00	17:59:00	210
POIN00031	SAL001	2023-11-22	08:00:00	11:59:00	300
POIN00032	SAL001	2023-11-22	12:00:00	17:59:00	280
POIN00033	SAL001	2023-11-23	08:00:00	11:59:00	250
POIN00034	SAL001	2023-11-23	12:00:00	17:59:00	240
POIN00035	SAL001	2023-11-24	08:00:00	11:59:00	220
POIN00036	SAL001	2023-11-24	12:00:00	17:59:00	210
POIN00041	SAL001	2023-11-29	08:00:00	11:59:00	300
POIN00042	SAL001	2023-11-29	12:00:00	17:59:00	280
POIN00043	SAL001	2023-11-30	08:00:00	11:59:00	250
POIN00044	SAL001	2023-11-30	12:00:00	17:59:00	240
POIN00045	SAL001	2023-12-01	08:00:00	11:59:00	220
POIN00046	SAL001	2023-12-01	12:00:00	17:59:00	210
POIN00051	SAL001	2023-12-06	08:00:00	11:59:00	300
POIN00052	SAL001	2023-12-06	12:00:00	17:59:00	280
POIN00053	SAL001	2023-12-07	08:00:00	11:59:00	250
POIN00054	SAL001	2023-12-07	12:00:00	17:59:00	240
POIN00055	SAL001	2023-12-08	08:00:00	11:59:00	220
POIN00056	SAL001	2023-12-08	12:00:00	17:59:00	210
POIN00061	SAL001	2023-12-13	08:00:00	11:59:00	300
POIN00062	SAL001	2023-12-13	12:00:00	17:59:00	280
POIN00010	SAL001	2023-11-07	12:00:00	17:59:00	290
POIN00017	SAL001	2023-11-13	08:00:00	11:59:00	320
POIN00018	SAL001	2023-11-13	12:00:00	17:59:00	330
POIN00019	SAL001	2023-11-14	08:00:00	11:59:00	300
POIN00020	SAL001	2023-11-14	12:00:00	17:59:00	290
POIN00027	SAL001	2023-11-20	08:00:00	11:59:00	320
POIN00028	SAL001	2023-11-20	12:00:00	17:59:00	330
POIN00029	SAL001	2023-11-21	08:00:00	11:59:00	300
POIN00030	SAL001	2023-11-21	12:00:00	17:59:00	290
POIN00037	SAL001	2023-11-27	08:00:00	11:59:00	320
POIN00038	SAL001	2023-11-27	12:00:00	17:59:00	330
POIN00039	SAL001	2023-11-28	08:00:00	11:59:00	300
POIN00040	SAL001	2023-11-28	12:00:00	17:59:00	290
POIN00047	SAL001	2023-12-04	08:00:00	11:59:00	320
POIN00048	SAL001	2023-12-04	12:00:00	17:59:00	330
POIN00049	SAL001	2023-12-05	08:00:00	11:59:00	300
POIN00050	SAL001	2023-12-05	12:00:00	17:59:00	290
POIN00057	SAL001	2023-12-11	08:00:00	11:59:00	320
POIN00058	SAL001	2023-12-11	12:00:00	17:59:00	330
POIN00059	SAL001	2023-12-12	08:00:00	11:59:00	300
POIN00060	SAL001	2023-12-12	12:00:00	17:59:00	290
\.


--
-- Data for Name: salle; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.salle (id_salle, nom, capacite_max, id_secteur) FROM stdin;
SAL001	Amphi A	150	SEC001
SAL002	Amphi B	150	SEC001
\.


--
-- Data for Name: secteur; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.secteur (id_secteur, nom) FROM stdin;
SECT0001	Secteur 1
SECT0002	Secteur 2
SECT0003	Secteur 3
SEC001	Secteur 1
\.


--
-- Data for Name: source_solaire; Type: TABLE DATA; Schema: public; Owner: mamisoa
--

COPY public.source_solaire (id_source, puissance_max, reserve_max_batterie, id_secteur) FROM stdin;
SRC00001	25000	19200	SEC001
\.


--
-- Name: besoin_secteur_id_besoin_seq; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.besoin_secteur_id_besoin_seq', 584, true);


--
-- Name: details_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.details_id_seq', 322, true);


--
-- Name: seq_coupure; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_coupure', 1, false);


--
-- Name: seq_meteo; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_meteo', 1, false);


--
-- Name: seq_pointage; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_pointage', 1, false);


--
-- Name: seq_salle; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_salle', 1, false);


--
-- Name: seq_secteur; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_secteur', 1, false);


--
-- Name: seq_source; Type: SEQUENCE SET; Schema: public; Owner: mamisoa
--

SELECT pg_catalog.setval('public.seq_source', 1, false);


--
-- Name: besoin_secteur besoin_secteur_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.besoin_secteur
    ADD CONSTRAINT besoin_secteur_pkey PRIMARY KEY (id_besoin);


--
-- Name: coupure coupure_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.coupure
    ADD CONSTRAINT coupure_pkey PRIMARY KEY (id);


--
-- Name: details details_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.details
    ADD CONSTRAINT details_pkey PRIMARY KEY (id);


--
-- Name: meteo meteo_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.meteo
    ADD CONSTRAINT meteo_pkey PRIMARY KEY (id);


--
-- Name: pointage pointage_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.pointage
    ADD CONSTRAINT pointage_pkey PRIMARY KEY (id);


--
-- Name: salle salle_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.salle
    ADD CONSTRAINT salle_pkey PRIMARY KEY (id_salle);


--
-- Name: secteur secteur_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.secteur
    ADD CONSTRAINT secteur_pkey PRIMARY KEY (id_secteur);


--
-- Name: source_solaire source_solaire_id_secteur_key; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.source_solaire
    ADD CONSTRAINT source_solaire_id_secteur_key UNIQUE (id_secteur);


--
-- Name: source_solaire source_solaire_pkey; Type: CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.source_solaire
    ADD CONSTRAINT source_solaire_pkey PRIMARY KEY (id_source);


--
-- Name: v_pointage_secteur _RETURN; Type: RULE; Schema: public; Owner: mamisoa
--

CREATE OR REPLACE VIEW public.v_pointage_secteur AS
 SELECT s.id_secteur,
    s.nom,
    p.daty,
    p.heure_debut,
    p.heure_fin,
    sum(p.nombre_personne) AS nombre_personne
   FROM ((public.salle b
     JOIN public.secteur s ON (((b.id_secteur)::text = (s.id_secteur)::text)))
     JOIN public.pointage p ON (((p.id_salle)::text = (b.id_salle)::text)))
  GROUP BY s.id_secteur, p.daty, p.heure_debut, p.heure_fin;


--
-- Name: besoin_secteur besoin_secteur_id_secteur_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.besoin_secteur
    ADD CONSTRAINT besoin_secteur_id_secteur_fkey FOREIGN KEY (id_secteur) REFERENCES public.secteur(id_secteur);


--
-- Name: coupure coupure_id_secteur_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.coupure
    ADD CONSTRAINT coupure_id_secteur_fkey FOREIGN KEY (id_secteur) REFERENCES public.secteur(id_secteur);


--
-- Name: pointage pointage_id_salle_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.pointage
    ADD CONSTRAINT pointage_id_salle_fkey FOREIGN KEY (id_salle) REFERENCES public.salle(id_salle);


--
-- Name: source_solaire source_solaire_id_secteur_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mamisoa
--

ALTER TABLE ONLY public.source_solaire
    ADD CONSTRAINT source_solaire_id_secteur_fkey FOREIGN KEY (id_secteur) REFERENCES public.secteur(id_secteur);


--
-- PostgreSQL database dump complete
--


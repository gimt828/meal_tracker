-- Table: public.facet_type

-- DROP TABLE IF EXISTS public.facet_type;

CREATE TABLE IF NOT EXISTS public.facet_type
(
    facet_type_id bigint NOT NULL DEFAULT nextval('facet_type_facet_type_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    CONSTRAINT facet_type_pkey PRIMARY KEY (facet_type_id),
    CONSTRAINT facet_type_name_key UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.facet_type
    OWNER to postgres;

COMMENT ON TABLE public.facet_type
    IS '대분류 테이블';

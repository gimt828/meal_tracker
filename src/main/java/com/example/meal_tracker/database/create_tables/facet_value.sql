-- Table: public.facet_value

-- DROP TABLE IF EXISTS public.facet_value;

CREATE TABLE IF NOT EXISTS public.facet_value
(
    facet_value_id bigint NOT NULL DEFAULT nextval('facet_value_facet_value_id_seq'::regclass),
    facet_type_id bigint NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    CONSTRAINT facet_value_pkey PRIMARY KEY (facet_value_id),
    CONSTRAINT facet_value_facet_type_id_name_key UNIQUE (facet_type_id, name),
    CONSTRAINT facet_value_facet_type_id_fkey FOREIGN KEY (facet_type_id)
        REFERENCES public.facet_type (facet_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.facet_value
    OWNER to postgres;
-- Index: idx_facet_value_type

-- DROP INDEX IF EXISTS public.idx_facet_value_type;

CREATE INDEX IF NOT EXISTS idx_facet_value_type
    ON public.facet_value USING btree
    (facet_type_id ASC NULLS LAST)
    TABLESPACE pg_default;

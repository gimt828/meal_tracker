-- Table: public.nutrition_statistics

-- DROP TABLE IF EXISTS public.nutrition_statistics;

CREATE TABLE IF NOT EXISTS public.nutrition_statistics
(
    stat_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    user_id integer NOT NULL,
    period_type character varying(20) COLLATE pg_catalog."default" NOT NULL,
    period_start date NOT NULL,
    period_end date,
    total_calories integer,
    total_burned integer,
    net_calories integer,
    carb_ratio numeric(5,2),
    protein_ratio numeric(5,2),
    fat_ratio numeric(5,2),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT nutrition_statistics_pkey PRIMARY KEY (stat_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.nutrition_statistics
    OWNER to postgres;

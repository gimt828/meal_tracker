-- Table: public.food

-- DROP TABLE IF EXISTS public.food;

CREATE TABLE IF NOT EXISTS public.food
(
    food_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name text COLLATE pg_catalog."default" NOT NULL,
    brand text COLLATE pg_catalog."default",
    serving_grams numeric(8,2),
    kcal_per_100g numeric(8,2),
    protein_g_100g numeric(8,2),
    fat_g_100g numeric(8,2),
    carb_g_100g numeric(8,2),
    sugar_g_100g numeric(8,2),
    sodium_mg_100g numeric(10,2),
    choles_mg_100g numeric(10,2),
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    CONSTRAINT food_pkey PRIMARY KEY (food_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.food
    OWNER to postgres;
-- Index: idx_food_name

-- DROP INDEX IF EXISTS public.idx_food_name;

CREATE INDEX IF NOT EXISTS idx_food_name
    ON public.food USING btree
    (name COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

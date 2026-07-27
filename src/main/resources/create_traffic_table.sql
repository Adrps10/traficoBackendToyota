-- ==============================================
-- CONECTAR A LA BASE DE DATOS
-- ==============================================
\c toyota_pachuca;

-- ==============================================
-- CREAR TABLA PARA HOJA DE TRÁFICO TOYOTA PACHUCA
-- ==============================================
CREATE TABLE IF NOT EXISTS traffic_records (
    id BIGSERIAL PRIMARY KEY,
    
    -- ENCABEZADO
    coach_name VARCHAR(100),
    record_date TIMESTAMP,
    greeters VARCHAR(100),
    
    -- DATOS DEL CLIENTE
    client_name VARCHAR(255) NOT NULL,
    entry_time TIMESTAMP,
    exit_time TIMESTAMP,
    first_visit VARCHAR(2),
    previous_visit BOOLEAN,
    appointment VARCHAR(2),
    internet_other VARCHAR(100),
    prospection VARCHAR(2),
    phone VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) NOT NULL,
    adv VARCHAR(100),
    required_model VARCHAR(100),
    
    -- ACTIVIDADES
    demo_estatica VARCHAR(50),
    prueba_manejo VARCHAR(50),
    hoja_opciones VARCHAR(50),
    plan_financiamiento VARCHAR(50),
    toyotour VARCHAR(50),
    auto_actual_avaluo VARCHAR(2),
    intervino_coach VARCHAR(2),
    
    -- SEGUIMIENTO
    comments TEXT,
    next_visit TIMESTAMP,
    
    -- METADATOS
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==============================================
-- CREAR ÍNDICES
-- ==============================================
CREATE INDEX IF NOT EXISTS idx_traffic_client_name ON traffic_records(client_name);
CREATE INDEX IF NOT EXISTS idx_traffic_phone ON traffic_records(phone);
CREATE INDEX IF NOT EXISTS idx_traffic_email ON traffic_records(email);
CREATE INDEX IF NOT EXISTS idx_traffic_coach_name ON traffic_records(coach_name);
CREATE INDEX IF NOT EXISTS idx_traffic_record_date ON traffic_records(record_date);
CREATE INDEX IF NOT EXISTS idx_traffic_timestamp ON traffic_records(timestamp DESC);

-- ==============================================
-- TRIGGER PARA ACTUALIZAR updated_at
-- ==============================================
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

DROP TRIGGER IF EXISTS update_traffic_records_updated_at ON traffic_records;
CREATE TRIGGER update_traffic_records_updated_at 
    BEFORE UPDATE ON traffic_records 
    FOR EACH ROW 
    EXECUTE FUNCTION update_updated_at_column();

-- ==============================================
-- DATOS INICIALES - COACHES DE VENTAS
-- ==============================================
CREATE TABLE IF NOT EXISTS coaches (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO coaches (name) VALUES 
    ('Coach 1'),
    ('Coach 2'),
    ('Coach 3'),
    ('Coach 4')
ON CONFLICT (name) DO NOTHING;

-- ==============================================
-- DATOS INICIALES - MODELOS TOYOTA
-- ==============================================
CREATE TABLE IF NOT EXISTS toyota_models (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO toyota_models (name) VALUES 
    ('Camry'),
    ('Corolla'),
    ('RAV4'),
    ('Tacoma'),
    ('Tundra'),
    ('Highlander'),
    ('Sienna'),
    ('4Runner'),
    ('Supra'),
    ('GR86'),
    ('Prius'),
    ('Sequoia')
ON CONFLICT (name) DO NOTHING;

-- ==============================================
-- DATOS INICIALES - OPCIONES PARA SELECTS
-- ==============================================
CREATE TABLE IF NOT EXISTS select_options (
    id BIGSERIAL PRIMARY KEY,
    category VARCHAR(50) NOT NULL,
    value VARCHAR(100) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(category, value)
);

-- Insertar opciones para actividades
INSERT INTO select_options (category, value) VALUES 
    ('demo_estatica', 'Sí'),
    ('demo_estatica', 'No'),
    ('demo_estatica', 'No aplica'),
    ('prueba_manejo', 'Sí'),
    ('prueba_manejo', 'No'),
    ('prueba_manejo', 'No aplica'),
    ('hoja_opciones', 'Sí'),
    ('hoja_opciones', 'No'),
    ('hoja_opciones', 'No aplica'),
    ('plan_financiamiento', 'Sí'),
    ('plan_financiamiento', 'No'),
    ('plan_financiamiento', 'No aplica'),
    ('toyotour', 'Sí'),
    ('toyotour', 'No'),
    ('toyotour', 'No aplica')
ON CONFLICT (category, value) DO NOTHING;
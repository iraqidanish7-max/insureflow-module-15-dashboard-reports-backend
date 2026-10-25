-- Insurance PAS Module 15: reporting schema
-- Run this file in MySQL Workbench or mysql client before starting the application.
CREATE DATABASE IF NOT EXISTS pas_reporting CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE pas_reporting;

CREATE TABLE IF NOT EXISTS report_master (
  report_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  report_name VARCHAR(150) NOT NULL,
  report_type VARCHAR(100) NOT NULL,
  description TEXT,
  created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uk_report_master_name (report_name)
);

CREATE TABLE IF NOT EXISTS report_schedule (
  schedule_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  report_id BIGINT NOT NULL,
  schedule_type ENUM('Daily','Weekly','Monthly','Quarterly','Yearly') NOT NULL,
  next_run_date DATETIME NOT NULL,
  email_to VARCHAR(250) NOT NULL,
  status ENUM('Active','Inactive') NOT NULL DEFAULT 'Active',
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_schedule_report FOREIGN KEY (report_id) REFERENCES report_master(report_id)
);

CREATE TABLE IF NOT EXISTS report_execution (
  execution_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  report_id BIGINT NOT NULL,
  executed_by BIGINT NULL,
  execution_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  execution_status ENUM('Success','Failed') NOT NULL,
  file_path VARCHAR(500),
  requested_report VARCHAR(100),
  CONSTRAINT fk_execution_report FOREIGN KEY (report_id) REFERENCES report_master(report_id),
  INDEX idx_execution_date (execution_date)
);

CREATE TABLE IF NOT EXISTS dashboard_widget (
  widget_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  widget_name VARCHAR(100) NOT NULL,
  widget_type VARCHAR(50) NOT NULL,
  sequence_no INT NOT NULL DEFAULT 1,
  status ENUM('Active','Inactive') NOT NULL DEFAULT 'Active'
);

CREATE TABLE IF NOT EXISTS analytics_snapshot (
  snapshot_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  snapshot_date DATE NOT NULL,
  total_customers INT NOT NULL DEFAULT 0,
  total_policies INT NOT NULL DEFAULT 0,
  premium_collected DECIMAL(15,2) NOT NULL DEFAULT 0.00,
  claims_paid DECIMAL(15,2) NOT NULL DEFAULT 0.00,
  revenue DECIMAL(15,2) NOT NULL DEFAULT 0.00,
  UNIQUE KEY uk_snapshot_date (snapshot_date)
);

INSERT IGNORE INTO report_master (report_id, report_name, report_type, description) VALUES
(1,'Customer Reports','CUSTOMER','Customer registration, KYC and demographic reports'),
(2,'Policy Reports','POLICY','Issued, active, expired, cancelled and renewal reports'),
(3,'Premium Reports','PREMIUM','Collection, pending premium, overdue and refund reports'),
(4,'Claims Reports','CLAIMS','Registered, pending, approved, rejected and settled claims'),
(5,'Underwriting Reports','UNDERWRITING','Underwriting cases, risk and medical pending reports'),
(6,'Agent Reports','AGENT','Sales, premium collection and agent performance reports'),
(7,'Financial Reports','FINANCIAL','Premium income, claim payments, refunds and revenue');

INSERT INTO dashboard_widget (widget_name, widget_type, sequence_no, status)
SELECT 'Total Customers','KPI',1,'Active' WHERE NOT EXISTS (SELECT 1 FROM dashboard_widget WHERE widget_name='Total Customers');
INSERT INTO dashboard_widget (widget_name, widget_type, sequence_no, status)
SELECT 'Active Policies','KPI',2,'Active' WHERE NOT EXISTS (SELECT 1 FROM dashboard_widget WHERE widget_name='Active Policies');
INSERT INTO dashboard_widget (widget_name, widget_type, sequence_no, status)
SELECT 'Premium Trend','LINE',3,'Active' WHERE NOT EXISTS (SELECT 1 FROM dashboard_widget WHERE widget_name='Premium Trend');
INSERT INTO dashboard_widget (widget_name, widget_type, sequence_no, status)
SELECT 'Policy Sales','BAR',4,'Active' WHERE NOT EXISTS (SELECT 1 FROM dashboard_widget WHERE widget_name='Policy Sales');

-- phpMyAdmin SQL Dump
-- version 4.0.4.2
-- http://www.phpmyadmin.net
--
-- Máquina: localhost
-- Data de Criação: 30-Jun-2016 às 11:40
-- Versão do servidor: 5.6.13
-- versão do PHP: 5.4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `proteko`
--
CREATE DATABASE IF NOT EXISTS `proteko` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `proteko`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `chamado`
--

CREATE TABLE IF NOT EXISTS `chamado` (
  `nChamado` int(11) NOT NULL AUTO_INCREMENT,
  `dtAbertura` varchar(50) NOT NULL,
  `assunto` varchar(200) NOT NULL,
  `id_unidade` int(11) NOT NULL,
  `status` varchar(50) NOT NULL,
  `id_tecnico` int(11) NOT NULL,
  `departamento` varchar(50) NOT NULL,
  `problemaRelatado` text NOT NULL,
  `solucao` text,
  `emailSolicitante` varchar(100) NOT NULL,
  `contato` varchar(50) NOT NULL,
  `dtConclusao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`nChamado`),
  KEY `id_unidade` (`id_unidade`),
  KEY `id_status` (`status`),
  KEY `id_tecnico` (`id_tecnico`,`departamento`),
  KEY `id_departamento` (`departamento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `chamado`
--

INSERT INTO `chamado` (`nChamado`, `dtAbertura`, `assunto`, `id_unidade`, `status`, `id_tecnico`, `departamento`, `problemaRelatado`, `solucao`, `emailSolicitante`, `contato`, `dtConclusao`) VALUES
(1, '20/03/2014', 'cancela com problema', 3461, '0', 1, '0', 'após a passagem do carro a cancela fica aberta.', NULL, 'guilhermekerpel@gmail.com', '5181219942', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tecnico`
--

CREATE TABLE IF NOT EXISTS `tecnico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `funcao` varchar(50) NOT NULL,
  `departamento` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefone` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `tecnico`
--

INSERT INTO `tecnico` (`id`, `nome`, `funcao`, `departamento`, `email`, `telefone`) VALUES
(1, 'João Paulo', 'oreia seca', 'cívil', 'joao@oreiaseca.com', '123456'),
(2, 'Guilherme Dalenogare Kerpel', 'técnico', 'técnico', 'guilhermekerpel@gmail.com', '5181219942'),
(3, 'rodrigo', 'tecnico', 'tecnico''', 'rofrigo@proteko.com.br', '81219874');

-- --------------------------------------------------------

--
-- Estrutura da tabela `unidade`
--

CREATE TABLE IF NOT EXISTS `unidade` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `telefone` varchar(50) NOT NULL,
  `cnpj` varchar(50) DEFAULT NULL,
  `inscricaoMunicipal` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `unidade`
--

INSERT INTO `unidade` (`id`, `nome`, `endereco`, `telefone`, `cnpj`, `inscricaoMunicipal`) VALUES
(3461, 'American Busines Square', 'soledade', '34242390234', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefone` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `email`, `telefone`) VALUES
(1, 'Guilherme Dalenogare Kerpel', 'guilhermekerpel@gmail.com', '5181219942'),
(2, 'Guilherme Dalenogare Kerpel', 'guilhermekerpel@gmail.com', '5181219942'),
(3, 'teste', 'tetss', 'qdslkjfhsdf'),
(4, 'Guilherme Dalenogare Kerpel', 'guilhermekerpel@gmail.com', '5181219942');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `chamado`
--
ALTER TABLE `chamado`
  ADD CONSTRAINT `chamado_ibfk_1` FOREIGN KEY (`id_unidade`) REFERENCES `unidade` (`id`),
  ADD CONSTRAINT `chamado_ibfk_2` FOREIGN KEY (`id_tecnico`) REFERENCES `tecnico` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

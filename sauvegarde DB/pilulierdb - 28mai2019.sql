-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 28 mai 2019 à 18:32
-- Version du serveur :  10.1.37-MariaDB
-- Version de PHP :  7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pilulierdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `cabinet`
--

CREATE TABLE `cabinet` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) COLLATE utf8_bin NOT NULL,
  `adresse` varchar(100) COLLATE utf8_bin NOT NULL,
  `cp` varchar(5) COLLATE utf8_bin NOT NULL,
  `ville` varchar(50) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `cabinet`
--

INSERT INTO `cabinet` (`id`, `nom`, `adresse`, `cp`, `ville`) VALUES
(17, 'Hopital Archet', '1 rue de la paix', '06100', 'nice');

-- --------------------------------------------------------

--
-- Structure de la table `dose`
--

CREATE TABLE `dose` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `dose`
--

INSERT INTO `dose` (`id`, `nom`) VALUES
(11, 'comprime'),
(13, 'cuillere a cafe'),
(12, 'cuillere a soupe'),
(14, 'sachet');

-- --------------------------------------------------------

--
-- Structure de la table `duree`
--

CREATE TABLE `duree` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `duree`
--

INSERT INTO `duree` (`id`, `nom`) VALUES
(4, 'jour'),
(6, 'mois'),
(5, 'semaine');

-- --------------------------------------------------------

--
-- Structure de la table `frequence`
--

CREATE TABLE `frequence` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `frequence`
--

INSERT INTO `frequence` (`id`, `nom`) VALUES
(3, 'jour'),
(4, 'mois'),
(5, 'semaine');

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE `medecin` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) COLLATE utf8_bin NOT NULL,
  `id_specialite` int(11) NOT NULL,
  `id_cabinet` int(11) NOT NULL,
  `telephone` varchar(20) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`id`, `nom`, `id_specialite`, `id_cabinet`, `telephone`, `email`) VALUES
(14, 'dr maboule', 12, 17, '0101010101', 'maboule@gmail.com'),
(15, 'sadoul', 12, 17, 'telephone', 'email');

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

CREATE TABLE `medicament` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `medicament`
--

INSERT INTO `medicament` (`id`, `nom`) VALUES
(17, 'doliprane'),
(16, 'ibuprofene'),
(18, 'levothyrox'),
(15, 'neomercazole');

-- --------------------------------------------------------

--
-- Structure de la table `prescription`
--

CREATE TABLE `prescription` (
  `id` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `id_medicament` int(11) NOT NULL,
  `id_medecin` int(11) NOT NULL,
  `nb_dose` decimal(2,1) NOT NULL,
  `id_dose` int(11) NOT NULL,
  `nb_frequence` int(11) NOT NULL,
  `id_frequence` int(11) NOT NULL,
  `matin` tinyint(1) NOT NULL,
  `midi` tinyint(1) NOT NULL,
  `soir` tinyint(1) NOT NULL,
  `nb_duree` int(11) NOT NULL,
  `id_duree` int(11) NOT NULL,
  `date_debut` varchar(30) COLLATE utf8_bin NOT NULL,
  `date_fin` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `prescription`
--

INSERT INTO `prescription` (`id`, `id_utilisateur`, `id_medicament`, `id_medecin`, `nb_dose`, `id_dose`, `nb_frequence`, `id_frequence`, `matin`, `midi`, `soir`, `nb_duree`, `id_duree`, `date_debut`, `date_fin`) VALUES
(1, 13, 17, 14, '1.0', 11, 2, 3, 1, 1, 0, 1, 6, '23/05/2019', '23/06/2019'),
(2, 14, 15, 15, '1.5', 11, 1, 3, 1, 0, 0, 6, 6, '24/05/2019', '24/11/2019');

-- --------------------------------------------------------

--
-- Structure de la table `prise`
--

CREATE TABLE `prise` (
  `id` int(11) NOT NULL,
  `id_prescription` int(11) NOT NULL,
  `date` varchar(30) COLLATE utf8_bin NOT NULL,
  `heure` varchar(30) COLLATE utf8_bin NOT NULL,
  `effectue` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `prise`
--

INSERT INTO `prise` (`id`, `id_prescription`, `date`, `heure`, `effectue`) VALUES
(159, 1, '23/05/2019', 'matin', 0),
(160, 1, '23/05/2019', 'midi', 0),
(161, 1, '24/05/2019', 'matin', 1),
(162, 1, '24/05/2019', 'midi', 1),
(163, 1, '25/05/2019', 'matin', 0),
(164, 1, '25/05/2019', 'midi', 0),
(165, 1, '26/05/2019', 'matin', 0),
(166, 1, '26/05/2019', 'midi', 0),
(167, 1, '27/05/2019', 'matin', 0),
(168, 1, '27/05/2019', 'midi', 0),
(169, 1, '28/05/2019', 'matin', 0),
(170, 1, '28/05/2019', 'midi', 0),
(171, 1, '29/05/2019', 'matin', 0),
(172, 1, '29/05/2019', 'midi', 0),
(173, 1, '30/05/2019', 'matin', 0),
(174, 1, '30/05/2019', 'midi', 0),
(175, 1, '31/05/2019', 'matin', 0),
(176, 1, '31/05/2019', 'midi', 0),
(177, 1, '01/06/2019', 'matin', 0),
(178, 1, '01/06/2019', 'midi', 0),
(179, 1, '02/06/2019', 'matin', 0),
(180, 1, '02/06/2019', 'midi', 0),
(181, 1, '03/06/2019', 'matin', 0),
(182, 1, '03/06/2019', 'midi', 0),
(183, 1, '04/06/2019', 'matin', 0),
(184, 1, '04/06/2019', 'midi', 0),
(185, 1, '05/06/2019', 'matin', 0),
(186, 1, '05/06/2019', 'midi', 0),
(187, 1, '06/06/2019', 'matin', 0),
(188, 1, '06/06/2019', 'midi', 0),
(189, 1, '07/06/2019', 'matin', 0),
(190, 1, '07/06/2019', 'midi', 0),
(191, 1, '08/06/2019', 'matin', 0),
(192, 1, '08/06/2019', 'midi', 0),
(193, 1, '09/06/2019', 'matin', 0),
(194, 1, '09/06/2019', 'midi', 0),
(195, 1, '10/06/2019', 'matin', 0),
(196, 1, '10/06/2019', 'midi', 0),
(197, 1, '11/06/2019', 'matin', 0),
(198, 1, '11/06/2019', 'midi', 0),
(199, 1, '12/06/2019', 'matin', 0),
(200, 1, '12/06/2019', 'midi', 0),
(201, 1, '13/06/2019', 'matin', 0),
(202, 1, '13/06/2019', 'midi', 0),
(203, 1, '14/06/2019', 'matin', 0),
(204, 1, '14/06/2019', 'midi', 0),
(205, 1, '15/06/2019', 'matin', 0),
(206, 1, '15/06/2019', 'midi', 0),
(207, 1, '16/06/2019', 'matin', 0),
(208, 1, '16/06/2019', 'midi', 0),
(209, 1, '17/06/2019', 'matin', 0),
(210, 1, '17/06/2019', 'midi', 0),
(211, 1, '18/06/2019', 'matin', 0),
(212, 1, '18/06/2019', 'midi', 0),
(213, 1, '19/06/2019', 'matin', 0),
(214, 1, '19/06/2019', 'midi', 0),
(215, 1, '20/06/2019', 'matin', 0),
(216, 1, '20/06/2019', 'midi', 0),
(217, 1, '21/06/2019', 'matin', 0),
(218, 1, '21/06/2019', 'midi', 0),
(219, 1, '22/06/2019', 'matin', 0),
(220, 1, '22/06/2019', 'midi', 0),
(221, 2, '24/05/2019', 'matin', 1),
(222, 2, '25/05/2019', 'matin', 0),
(223, 2, '26/05/2019', 'matin', 0),
(224, 2, '27/05/2019', 'matin', 0),
(225, 2, '28/05/2019', 'matin', 0),
(226, 2, '29/05/2019', 'matin', 0),
(227, 2, '30/05/2019', 'matin', 0),
(228, 2, '31/05/2019', 'matin', 0),
(229, 2, '01/06/2019', 'matin', 0),
(230, 2, '02/06/2019', 'matin', 0),
(231, 2, '03/06/2019', 'matin', 0),
(232, 2, '04/06/2019', 'matin', 0),
(233, 2, '05/06/2019', 'matin', 0),
(234, 2, '06/06/2019', 'matin', 0),
(235, 2, '07/06/2019', 'matin', 0),
(236, 2, '08/06/2019', 'matin', 0),
(237, 2, '09/06/2019', 'matin', 0),
(238, 2, '10/06/2019', 'matin', 0),
(239, 2, '11/06/2019', 'matin', 0),
(240, 2, '12/06/2019', 'matin', 0),
(241, 2, '13/06/2019', 'matin', 0),
(242, 2, '14/06/2019', 'matin', 0),
(243, 2, '15/06/2019', 'matin', 0),
(244, 2, '16/06/2019', 'matin', 0),
(245, 2, '17/06/2019', 'matin', 0),
(246, 2, '18/06/2019', 'matin', 0),
(247, 2, '19/06/2019', 'matin', 0),
(248, 2, '20/06/2019', 'matin', 0),
(249, 2, '21/06/2019', 'matin', 0),
(250, 2, '22/06/2019', 'matin', 0),
(251, 2, '23/06/2019', 'matin', 0),
(252, 2, '24/06/2019', 'matin', 0),
(253, 2, '25/06/2019', 'matin', 0),
(254, 2, '26/06/2019', 'matin', 0),
(255, 2, '27/06/2019', 'matin', 0),
(256, 2, '28/06/2019', 'matin', 0),
(257, 2, '29/06/2019', 'matin', 0),
(258, 2, '30/06/2019', 'matin', 0),
(259, 2, '01/07/2019', 'matin', 0),
(260, 2, '02/07/2019', 'matin', 0),
(261, 2, '03/07/2019', 'matin', 0),
(262, 2, '04/07/2019', 'matin', 0),
(263, 2, '05/07/2019', 'matin', 0),
(264, 2, '06/07/2019', 'matin', 0),
(265, 2, '07/07/2019', 'matin', 0),
(266, 2, '08/07/2019', 'matin', 0),
(267, 2, '09/07/2019', 'matin', 0),
(268, 2, '10/07/2019', 'matin', 0),
(269, 2, '11/07/2019', 'matin', 0),
(270, 2, '12/07/2019', 'matin', 0),
(271, 2, '13/07/2019', 'matin', 0),
(272, 2, '14/07/2019', 'matin', 0),
(273, 2, '15/07/2019', 'matin', 0),
(274, 2, '16/07/2019', 'matin', 0),
(275, 2, '17/07/2019', 'matin', 0),
(276, 2, '18/07/2019', 'matin', 0),
(277, 2, '19/07/2019', 'matin', 0),
(278, 2, '20/07/2019', 'matin', 0),
(279, 2, '21/07/2019', 'matin', 0),
(280, 2, '22/07/2019', 'matin', 0),
(281, 2, '23/07/2019', 'matin', 0),
(282, 2, '24/07/2019', 'matin', 0),
(283, 2, '25/07/2019', 'matin', 0),
(284, 2, '26/07/2019', 'matin', 0),
(285, 2, '27/07/2019', 'matin', 0),
(286, 2, '28/07/2019', 'matin', 0),
(287, 2, '29/07/2019', 'matin', 0),
(288, 2, '30/07/2019', 'matin', 0),
(289, 2, '31/07/2019', 'matin', 0),
(290, 2, '01/08/2019', 'matin', 0),
(291, 2, '02/08/2019', 'matin', 0),
(292, 2, '03/08/2019', 'matin', 0),
(293, 2, '04/08/2019', 'matin', 0),
(294, 2, '05/08/2019', 'matin', 0),
(295, 2, '06/08/2019', 'matin', 0),
(296, 2, '07/08/2019', 'matin', 0),
(297, 2, '08/08/2019', 'matin', 0),
(298, 2, '09/08/2019', 'matin', 0),
(299, 2, '10/08/2019', 'matin', 0),
(300, 2, '11/08/2019', 'matin', 0),
(301, 2, '12/08/2019', 'matin', 0),
(302, 2, '13/08/2019', 'matin', 0),
(303, 2, '14/08/2019', 'matin', 0),
(304, 2, '15/08/2019', 'matin', 0),
(305, 2, '16/08/2019', 'matin', 0),
(306, 2, '17/08/2019', 'matin', 0),
(307, 2, '18/08/2019', 'matin', 0),
(308, 2, '19/08/2019', 'matin', 0),
(309, 2, '20/08/2019', 'matin', 0),
(310, 2, '21/08/2019', 'matin', 0),
(311, 2, '22/08/2019', 'matin', 0),
(312, 2, '23/08/2019', 'matin', 0),
(313, 2, '24/08/2019', 'matin', 0),
(314, 2, '25/08/2019', 'matin', 0),
(315, 2, '26/08/2019', 'matin', 0),
(316, 2, '27/08/2019', 'matin', 0),
(317, 2, '28/08/2019', 'matin', 0),
(318, 2, '29/08/2019', 'matin', 0),
(319, 2, '30/08/2019', 'matin', 0),
(320, 2, '31/08/2019', 'matin', 0),
(321, 2, '01/09/2019', 'matin', 0),
(322, 2, '02/09/2019', 'matin', 0),
(323, 2, '03/09/2019', 'matin', 0),
(324, 2, '04/09/2019', 'matin', 0),
(325, 2, '05/09/2019', 'matin', 0),
(326, 2, '06/09/2019', 'matin', 0),
(327, 2, '07/09/2019', 'matin', 0),
(328, 2, '08/09/2019', 'matin', 0),
(329, 2, '09/09/2019', 'matin', 0),
(330, 2, '10/09/2019', 'matin', 0),
(331, 2, '11/09/2019', 'matin', 0),
(332, 2, '12/09/2019', 'matin', 0),
(333, 2, '13/09/2019', 'matin', 0),
(334, 2, '14/09/2019', 'matin', 0),
(335, 2, '15/09/2019', 'matin', 0),
(336, 2, '16/09/2019', 'matin', 0),
(337, 2, '17/09/2019', 'matin', 0),
(338, 2, '18/09/2019', 'matin', 0),
(339, 2, '19/09/2019', 'matin', 0),
(340, 2, '20/09/2019', 'matin', 0),
(341, 2, '21/09/2019', 'matin', 0),
(342, 2, '22/09/2019', 'matin', 0),
(343, 2, '23/09/2019', 'matin', 0),
(344, 2, '24/09/2019', 'matin', 0),
(345, 2, '25/09/2019', 'matin', 0),
(346, 2, '26/09/2019', 'matin', 0),
(347, 2, '27/09/2019', 'matin', 0),
(348, 2, '28/09/2019', 'matin', 0),
(349, 2, '29/09/2019', 'matin', 0),
(350, 2, '30/09/2019', 'matin', 0),
(351, 2, '01/10/2019', 'matin', 0),
(352, 2, '02/10/2019', 'matin', 0),
(353, 2, '03/10/2019', 'matin', 0),
(354, 2, '04/10/2019', 'matin', 0),
(355, 2, '05/10/2019', 'matin', 0),
(356, 2, '06/10/2019', 'matin', 0),
(357, 2, '07/10/2019', 'matin', 0),
(358, 2, '08/10/2019', 'matin', 0),
(359, 2, '09/10/2019', 'matin', 0),
(360, 2, '10/10/2019', 'matin', 0),
(361, 2, '11/10/2019', 'matin', 0),
(362, 2, '12/10/2019', 'matin', 0),
(363, 2, '13/10/2019', 'matin', 0),
(364, 2, '14/10/2019', 'matin', 0),
(365, 2, '15/10/2019', 'matin', 0),
(366, 2, '16/10/2019', 'matin', 0),
(367, 2, '17/10/2019', 'matin', 0),
(368, 2, '18/10/2019', 'matin', 0),
(369, 2, '19/10/2019', 'matin', 0),
(370, 2, '20/10/2019', 'matin', 0),
(371, 2, '21/10/2019', 'matin', 0),
(372, 2, '22/10/2019', 'matin', 0),
(373, 2, '23/10/2019', 'matin', 0),
(374, 2, '24/10/2019', 'matin', 0),
(375, 2, '25/10/2019', 'matin', 0),
(376, 2, '26/10/2019', 'matin', 0),
(377, 2, '27/10/2019', 'matin', 0),
(378, 2, '28/10/2019', 'matin', 0),
(379, 2, '29/10/2019', 'matin', 0),
(380, 2, '30/10/2019', 'matin', 0),
(381, 2, '31/10/2019', 'matin', 0),
(382, 2, '01/11/2019', 'matin', 0),
(383, 2, '02/11/2019', 'matin', 0),
(384, 2, '03/11/2019', 'matin', 0),
(385, 2, '04/11/2019', 'matin', 0),
(386, 2, '05/11/2019', 'matin', 0),
(387, 2, '06/11/2019', 'matin', 0),
(388, 2, '07/11/2019', 'matin', 0),
(389, 2, '08/11/2019', 'matin', 0),
(390, 2, '09/11/2019', 'matin', 0),
(391, 2, '10/11/2019', 'matin', 0),
(392, 2, '11/11/2019', 'matin', 0),
(393, 2, '12/11/2019', 'matin', 0),
(394, 2, '13/11/2019', 'matin', 0),
(395, 2, '14/11/2019', 'matin', 0),
(396, 2, '15/11/2019', 'matin', 0),
(397, 2, '16/11/2019', 'matin', 0),
(398, 2, '17/11/2019', 'matin', 0),
(399, 2, '18/11/2019', 'matin', 0),
(400, 2, '19/11/2019', 'matin', 0),
(401, 2, '20/11/2019', 'matin', 0),
(402, 2, '21/11/2019', 'matin', 0),
(403, 2, '22/11/2019', 'matin', 0),
(404, 2, '23/11/2019', 'matin', 0);

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
--

CREATE TABLE `rdv` (
  `id` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `id_medecin` int(11) NOT NULL,
  `date` varchar(30) COLLATE utf8_bin NOT NULL,
  `heure` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `rdv`
--

INSERT INTO `rdv` (`id`, `id_utilisateur`, `id_medecin`, `date`, `heure`) VALUES
(11, 11, 14, '25/05/2019', '14:00');

-- --------------------------------------------------------

--
-- Structure de la table `specialite`
--

CREATE TABLE `specialite` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `specialite`
--

INSERT INTO `specialite` (`id`, `nom`) VALUES
(12, 'endocrinologue'),
(10, 'generaliste'),
(11, 'rhumatologue');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`) VALUES
(11, 'Bob'),
(14, 'Hugo'),
(12, 'John'),
(13, 'Mike');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_medecin`
--

CREATE TABLE `utilisateur_medecin` (
  `id_utilisateur` int(11) NOT NULL,
  `id_medecin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `utilisateur_medecin`
--

INSERT INTO `utilisateur_medecin` (`id_utilisateur`, `id_medecin`) VALUES
(11, 14),
(13, 14),
(14, 15);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `cabinet`
--
ALTER TABLE `cabinet`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Index pour la table `dose`
--
ALTER TABLE `dose`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Index pour la table `duree`
--
ALTER TABLE `duree`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Index pour la table `frequence`
--
ALTER TABLE `frequence`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Index pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_specialite` (`id_specialite`),
  ADD KEY `id_cabinet` (`id_cabinet`);

--
-- Index pour la table `medicament`
--
ALTER TABLE `medicament`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Index pour la table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_medicament` (`id_medicament`),
  ADD KEY `id_dose` (`id_dose`),
  ADD KEY `id_frequence` (`id_frequence`),
  ADD KEY `id_duree` (`id_duree`),
  ADD KEY `id_medecin` (`id_medecin`),
  ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Index pour la table `prise`
--
ALTER TABLE `prise`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_prescription` (`id_prescription`);

--
-- Index pour la table `rdv`
--
ALTER TABLE `rdv`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_utilisateur` (`id_utilisateur`),
  ADD KEY `id_medecin` (`id_medecin`);

--
-- Index pour la table `specialite`
--
ALTER TABLE `specialite`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Index pour la table `utilisateur_medecin`
--
ALTER TABLE `utilisateur_medecin`
  ADD PRIMARY KEY (`id_utilisateur`,`id_medecin`),
  ADD KEY `utilisateur_medecin_ibfk_1` (`id_medecin`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `cabinet`
--
ALTER TABLE `cabinet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `dose`
--
ALTER TABLE `dose`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `duree`
--
ALTER TABLE `duree`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `frequence`
--
ALTER TABLE `frequence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `medecin`
--
ALTER TABLE `medecin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `medicament`
--
ALTER TABLE `medicament`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `prise`
--
ALTER TABLE `prise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=405;

--
-- AUTO_INCREMENT pour la table `rdv`
--
ALTER TABLE `rdv`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `specialite`
--
ALTER TABLE `specialite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD CONSTRAINT `medecin_ibfk_1` FOREIGN KEY (`id_specialite`) REFERENCES `specialite` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `medecin_ibfk_2` FOREIGN KEY (`id_cabinet`) REFERENCES `cabinet` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`id_dose`) REFERENCES `dose` (`id`),
  ADD CONSTRAINT `prescription_ibfk_3` FOREIGN KEY (`id_duree`) REFERENCES `duree` (`id`),
  ADD CONSTRAINT `prescription_ibfk_4` FOREIGN KEY (`id_frequence`) REFERENCES `frequence` (`id`),
  ADD CONSTRAINT `prescription_ibfk_5` FOREIGN KEY (`id_medicament`) REFERENCES `medicament` (`id`),
  ADD CONSTRAINT `prescription_ibfk_6` FOREIGN KEY (`id_medecin`) REFERENCES `medecin` (`id`),
  ADD CONSTRAINT `prescription_ibfk_7` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `prise`
--
ALTER TABLE `prise`
  ADD CONSTRAINT `prise_ibfk_1` FOREIGN KEY (`id_prescription`) REFERENCES `prescription` (`id`);

--
-- Contraintes pour la table `rdv`
--
ALTER TABLE `rdv`
  ADD CONSTRAINT `rdv_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `rdv_ibfk_2` FOREIGN KEY (`id_medecin`) REFERENCES `medecin` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `utilisateur_medecin`
--
ALTER TABLE `utilisateur_medecin`
  ADD CONSTRAINT `utilisateur_medecin_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `utilisateur_medecin_ibfk_2` FOREIGN KEY (`id_medecin`) REFERENCES `medecin` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

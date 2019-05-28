-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 24 mai 2019 à 10:21
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
(14, 'dr maboule', 12, 17, '0101010101', 'maboule@gmail.com');

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
  `nb_dose` int(11) NOT NULL,
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
(1, 13, 17, 14, 1, 11, 2, 3, 1, 1, 0, 1, 6, '23/05/2019', '23/06/2019');

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
(220, 1, '22/06/2019', 'midi', 0);

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
(13, 14);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `medicament`
--
ALTER TABLE `medicament`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `prise`
--
ALTER TABLE `prise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=221;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

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

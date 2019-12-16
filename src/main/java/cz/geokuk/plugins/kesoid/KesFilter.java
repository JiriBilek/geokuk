package cz.geokuk.plugins.kesoid;

import java.util.Set;

import cz.geokuk.plugins.kesoid.genetika.*;
import cz.geokuk.plugins.kesoid.kind.kes.Kes;
import cz.geokuk.plugins.vylety.EVylet;
import cz.geokuk.plugins.vylety.VyletModel;

public class KesFilter {

	private FilterDefinition filterDefinition;
	private Set<Alela> nechteneAlely;
	private QualAlelaNames jmenaNechtenychAlel;

	private VyletModel vyletModel;

	public KesFilter() {}

	private IndexMap<Genotyp, Boolean> uzFiltrovane;
	// public void onEvent(IgnoreListChangedEvent event) {
	// if (filterDefinition.getPrahVyletu() != EVylet.VSECHNY) {
	// kesoidModel.spustFiltrovani();
	// }
	//
	// }

	// public void onEvent(CestyChangedEvent aEvent) {
	// EVylet evylPrah = filterDefinition.getPrahVyletu();
	//
	// if (evylPrah == EVylet.JEN_V_CESTE) {
	// Set<Wpt> wpts = new HashSet<Wpt>();
	// FUtil.addAll(wpts, aEvent.getDoc().getWpts());
	// if (! wpts.equals(jenTytoVyletoveWaypointyZobrazit)) {
	// jenTytoVyletoveWaypointyZobrazit = wpts;
	// kesoidModel.spustFiltrovani();
	// }
	//
	// }
	// else {
	// jenTytoVyletoveWaypointyZobrazit = null;
	// }
	// }
	//

	/**
	 *
	 */
	public void done() {
		System.out.println("CICI: " + citac1 + "/" + citac2 + " " + uzFiltrovane);
		nechteneAlely = null;
		uzFiltrovane = null;
	}

	/**
	 * @return the filterDefinition
	 */
	public FilterDefinition getFilterDefinition() {
		return filterDefinition;
	}

	/**
	 * @return the jmenaNechtenychAlel
	 */
	public QualAlelaNames getJmenaNechtenychAlel() {
		return jmenaNechtenychAlel;
	}

	int citac1;
	int citac2;
	/**
	 *
	 */
	public void init() {
		nechteneAlely = null;
		uzFiltrovane = new IndexMap<>();
		citac1 = 0;
		citac2 = 0;
	}

	public void inject(final VyletModel vyletModel) {
		this.vyletModel = vyletModel;
	}



	public boolean isFiltered(final Wpt aWpt) {
		try {
			final Genotyp genotyp = aWpt.getGenotyp();
			final Genom genom = genotyp.getGenom();
			if (jmenaNechtenychAlel != null) {
				citac1 ++;
				if (nechteneAlely == null) {
					nechteneAlely = genom.searchAlelasByQualNames(jmenaNechtenychAlel);
				}
				final boolean chceme = uzFiltrovane.computeIfAbsent(genotyp, g -> {
					// zbytečně se nepočítá znovu a znovu pro každý stejný genotyp
					citac2 ++;
					return ! g.hasAny(nechteneAlely);
				});
				if (!chceme) {
					return false;
				}
			}

			// if (aWpt.getType() != AWptType.CACHE && ! wptTypes.contains(aWpt.getType())) return false;

			final Kesoid kesoid = aWpt.getKesoid();

			if (filterDefinition.isJenFinalUNalezenych()) {
				if (kesoid.getVztah() == EKesVztah.FOUND || kesoid.getVztah() == EKesVztah.OWN) {
					if (aWpt != kesoid.getMainWpt()) {
						return false;
					}
				}
			}

			if (kesoid instanceof Kes) {
				final Kes kes = (Kes) kesoid;

				if (kes.getVztah() == EKesVztah.NORMAL) { // jen u nenalezených
					if (kes.hasValidFinal() && filterDefinition.isJenDoTerenuUNenalezenych() && !aWpt.nutnyKLusteni() && !Wpt.TRADITIONAL_CACHE.equals(kes.getFirstWpt().getSym())) {
						return false;
					}
				}

				if (kes.getHodnoceni() != Kes.NENI_HODNOCENI) {
					if (kes.getHodnoceni() < filterDefinition.getPrahHodnoceni()) {
						return false;
					}
				}
				if (kes.getBestOf() != Kes.NENI_HODNOCENI) {
					if (kes.getBestOf() < filterDefinition.getPrahBestOf()) {
						return false;
					}
				}
				if (kes.getFavorit() != Kes.NENI_HODNOCENI) {
					if (kes.getFavorit() < filterDefinition.getPrahFavorit()) {
						return false;
					}
				}
			}
			if (vyletModel != null) {
				final EVylet evylKes = vyletModel.get(kesoid);
				final EVylet evylPrah = filterDefinition.getPrahVyletu();
				if (evylKes.ordinal() < evylPrah.ordinal()) {
					return false;
				}
			}
			return true;
		} catch (final Exception e) {
			throw new RuntimeException("Filtrovani waypointu: " + aWpt, e);

		}

	}

	public void setDefaults() {
		// Atom.of(AWptType.FINAL_LOCATION);
		filterDefinition = new FilterDefinition();
	}

	/**
	 * @param filterDefinition
	 *            the filterDefinition to set
	 */
	public void setFilterDefinition(final FilterDefinition filterDefinition) {
		this.filterDefinition = filterDefinition;
	}

	/**
	 * @param jmenaNechtenychAlel
	 *            the jmenaNechtenychAlel to set
	 */
	public void setJmenaNechtenychAlel(final QualAlelaNames jmenaNechtenychAlel) {
		this.jmenaNechtenychAlel = jmenaNechtenychAlel;
		nechteneAlely = null;
	}

}

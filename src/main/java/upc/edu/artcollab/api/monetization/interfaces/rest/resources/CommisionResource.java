/**
 * @summary
 * This file contains the CommisionResource class, which is used to define the methods that must be implemented by the CommisionResource class.
 * This class defines the methods that must be implemented by the CommisionResource class.
 */

package upc.edu.artcollab.api.monetization.interfaces.rest.resources;

import java.util.Date;

public record CommisionResource(Long id, double amount, String content) {
}

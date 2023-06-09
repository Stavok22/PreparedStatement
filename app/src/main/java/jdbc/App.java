/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jdbc;

import services.Database;
import services.DatabaseInitService;
import services.DatabasePopulateService;
import services.DatabaseQueryService;



public class App {


    public static void main(String[] args)  {
        Database database = Database.getInstanse();
        DatabaseInitService.main(args);
        try {
            DatabasePopulateService.main(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(new DatabaseQueryService().findLongestProject(database));
        System.out.println(new DatabaseQueryService().findMaxSalaryWorker(database));
        System.out.println(new DatabaseQueryService().findMaxProjectCountClient(database));
        System.out.println(new DatabaseQueryService().findProjectPrices(database));
        System.out.println(new DatabaseQueryService().findYoungestEldestWorkers(database));
    }
}

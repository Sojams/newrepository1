package jsonproject;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Scanner;



import org.json.simple.parser.ParseException;



public class Readdatafromjsonfile {   

		           public static void main(String[] args) throws ParseException, IOException {
		            	
		               
		            	 Scanner scanner = new Scanner(System.in);
				     
				      while (true) {
		                    System.out.println("\nOptions:");
		                    System.out.println("1. Get temperature");
		                    System.out.println("2. Get Wind Speed");
		                   System.out.println("3. Get Pressure");
		                    System.out.println("0. Exit");

		                    System.out.print("Enter your choice (0-3): ");
		                    int choice = scanner.nextInt();
		                    
						
		                   switch (choice) {
		                        case 1:
		                            getTemperature(scanner);
		                            break;
		                       case 2:
		                           getWindSpeed(scanner);
		                            break;
		                        case 3:
		                            getPressure(scanner);
		                           break;
		                        case 0:
		                            System.out.println("Exiting...");
		                            scanner.close();
		                            return;
		                        default:
		                           System.out.println("Invalid choice. Please try again.");
		                    }
		                   
								
								
							}
		    		        
		                }
		            

					

					
		            private static void getTemperature(Scanner scanner) throws IOException, ParseException {
		                System.out.print("Enter the date (YYYY-MM-DD): ");
		              
		                
		            
							
						}
		            

		            private static void getWindSpeed(Scanner scanner) throws IOException {
		                System.out.print("Enter the date (YYYY-MM-DD): ");
		                String date = scanner.next();
		                String windSpeed = fetchDataFromAPI(date, "wind_speed");
		                System.out.println("Wind speed for " + date + ": " + windSpeed + " m/s");
		            }

		            private static void getPressure(Scanner scanner) throws IOException {
		                System.out.print("Enter the date (YYYY-MM-DD): ");
		                String date = scanner.next();
		                String pressure = fetchDataFromAPI(date, "pressure");
		                System.out.println("Pressure for " + date + ": " + pressure + " hPa");
		            }

		            private static String fetchDataFromAPI(String date, String field) throws IOException {
		                String API_ENDPOINT = null;
						// Construct the API URL with the date parameter
		                URL url = new URL(API_ENDPOINT + "?date=" + date);
		                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		                connection.setRequestMethod("GET");
		                connection.connect();

		                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
		                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		                    StringBuilder response = new StringBuilder();
		                    String line;
		                    while ((line = reader.readLine()) != null) {
		                        response.append(line);
		                    }
		                    reader.close();

		                 
		                    return parseJson(response.toString(), field);
		                } else {
		                    System.out.println("Failed to fetch data from the API.");
		                    return null;
		                }
		            }

		          
		            private static String parseJson(String jsonData, String field) {
		                
		                return jsonData.contains("\"" + field + "\":") ? jsonData.split("\"" + field + "\":\"")[1].split("\",")[0] : null;
		            }
		        }
		    

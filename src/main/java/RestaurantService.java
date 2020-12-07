import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();
    private List<Item> selectedItems = new ArrayList<Item>();
    private int totalCost = 0;

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {

        for (int i = 0; i < restaurants.size(); i++)
        {
            if (restaurantName.equalsIgnoreCase(restaurants.get(i).getName()))
                return restaurants.get(i);
            else if (i == restaurants.size() - 1)
                throw new restaurantNotFoundException(restaurantName);

        }
        return null;

    }

    public void selectItem(String name, int price)
    {
        Item newItem = new Item(name,price);
        selectedItems.add(newItem);
        totalCost += price;

    }

    public void unselectItem(String name, int price)
    {
        for (Item currentSelectedItem: selectedItems) {
            if (currentSelectedItem.getName().equalsIgnoreCase(name))
            {
                selectedItems.remove(new Item(name, price));
                totalCost -= price;
            }
        }

    }

    public List<Item> getSelectedItems() {

        return selectedItems;
    }

    public int getTotalCost()
    {
        return totalCost;
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if (restaurantToBeRemoved == null)
            throw new restaurantNotFoundException(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

}

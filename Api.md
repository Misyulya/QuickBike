Objects:
* [?&lt;obj: Bike&gt;](#obj-bike)
* Shop
* Company
* Order
* Profile

# Используемые объекты

## Общие объекты

### &lt;obj: Bike&gt;
```javascript
{
  id: <pint>,
  shopId: <int>,
  photo: <string(url)>
  workdayPrice: <obj:Price>,
  weekendPrice: <obj:Price>,
  name: <str>,
  typeId: <int> [1 - male, 2 - female, 3 - kids]
}
```

### &lt;obj: Price&gt;
```javascript
{
  id: <pint>,
  hour: <int>,
  threeHours: <int>,
  day: <int>,
  week: <int>
}
```

### &lt;obj: Accessory&gt;
```javascript
{
  id: <pint>,
  shopId: <int>,
  price: <int>,
  name: <str>
}
```

### &lt;obj: Order&gt;
```javascript
{
  id: <pint>,
  bikeIds: <[int]>,
  accessoryIds: <[int]>,
  invoice: <int>,
  clientId: <int>
  from: <str(date)>,
  to: <str(date)>
}
```

### &lt;obj: Shop&gt;
```javascript
{
  id: <pint>,
  companyId: <int>,
  name: <str>,
  photo: <string(url)>,
  contactInfo: <obj:ContactInfo>
}
```

### &lt;obj: Company&gt;
```javascript
{
  id: <pint>,
  name: <str>,
  photo: <string(url)>,
  contactInfo: <obj:ContactInfo>
}
```

### &lt;obj: ContactInfo&gt;
```javascript
{
  id: <pint>,
  address: <str>,
  phone: <str>,
  ?mail: <str>,
  location: {
    latitude: <double>,
    longitude: <double>
  }
  ...
}
```

### &lt;obj: Profile&gt;
```javascript
{
  id: <pint>,
  firstName: <str>,
  lastName: <str>,
  contactInfo: <obj:ContactInfo>
  ...
}
```


### &lt;obj: Profile&gt;
```javascript
{
  id: <pint>,
  firstName: <str>,
  lastName: <str>,
  contactInfo: <obj:ContactInfo>
  ...
}
```

# API

### readShops

Возвращает список магазинов.

#### Request

```javascript
{
  filter:{
	  order: {
      from: <str(date)>,
      to: <str(date),
    },
    bike: {
      typeIds: <[int]>,
      count: <int>
    }
      
  }
}
```

#### Response

```javascript
{
	?ok: {
		shops: [<obj: Shop>, ...],
	},
	?errors: [...]
}
```
---


### readBikes

Возвращает список магазинов.

#### Request

```javascript
{
  filter:{
	  order: {
      from: <str(date)>,
      to: <str(date),
    },
    bike: {
      typeIds: <[int]>,
      count: <int>
    }
      
  }
}
```

#### Response

```javascript
{
	?ok: {
		bikes: [<obj: Bike>, ...],
	},
	?errors: [...]
}
```
---


### createOrder

Создаёт бронь для велосипедов.

#### Request

```javascript
{
  bikeIds: [int],
  from: <str(date)>,
  to: <str(date)>
}
```

#### Response

```javascript
{
	?ok: {
	  order: [<obj: Order>, ...],
	},
	?errors: [...]
}
```


import { IWishList } from 'app/shared/model/wish-list.model';
import { ICategory } from 'app/shared/model/category.model';

export interface IProduct {
  id?: number;
  title?: string;
  keywords?: string;
  description?: string;
  rating?: number;
  wishList?: IWishList;
  categories?: ICategory[];
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public title?: string,
    public keywords?: string,
    public description?: string,
    public rating?: number,
    public wishList?: IWishList,
    public categories?: ICategory[]
  ) {}
}

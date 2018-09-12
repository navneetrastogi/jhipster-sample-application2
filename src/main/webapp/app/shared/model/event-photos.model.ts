export interface IEventPhotos {
  id?: string;
  eventid?: number;
  imageUrls?: string;
  likes?: string;
  seen?: string;
}

export const defaultValue: Readonly<IEventPhotos> = {};

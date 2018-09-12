export const enum FeatureType {
  STANDARD = 'STANDARD',
  PREMIUM = 'PREMIUM'
}

export interface IFeature {
  id?: string;
  name?: string;
  featureType?: FeatureType;
}

export const defaultValue: Readonly<IFeature> = {};

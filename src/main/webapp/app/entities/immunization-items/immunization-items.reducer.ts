import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IImmunizationItems, defaultValue } from 'app/shared/model/immunization-items.model';

export const ACTION_TYPES = {
  FETCH_IMMUNIZATIONITEMS_LIST: 'immunizationItems/FETCH_IMMUNIZATIONITEMS_LIST',
  FETCH_IMMUNIZATIONITEMS: 'immunizationItems/FETCH_IMMUNIZATIONITEMS',
  CREATE_IMMUNIZATIONITEMS: 'immunizationItems/CREATE_IMMUNIZATIONITEMS',
  UPDATE_IMMUNIZATIONITEMS: 'immunizationItems/UPDATE_IMMUNIZATIONITEMS',
  DELETE_IMMUNIZATIONITEMS: 'immunizationItems/DELETE_IMMUNIZATIONITEMS',
  RESET: 'immunizationItems/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IImmunizationItems>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ImmunizationItemsState = Readonly<typeof initialState>;

// Reducer

export default (state: ImmunizationItemsState = initialState, action): ImmunizationItemsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_IMMUNIZATIONITEMS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_IMMUNIZATIONITEMS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_IMMUNIZATIONITEMS):
    case REQUEST(ACTION_TYPES.UPDATE_IMMUNIZATIONITEMS):
    case REQUEST(ACTION_TYPES.DELETE_IMMUNIZATIONITEMS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_IMMUNIZATIONITEMS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_IMMUNIZATIONITEMS):
    case FAILURE(ACTION_TYPES.CREATE_IMMUNIZATIONITEMS):
    case FAILURE(ACTION_TYPES.UPDATE_IMMUNIZATIONITEMS):
    case FAILURE(ACTION_TYPES.DELETE_IMMUNIZATIONITEMS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_IMMUNIZATIONITEMS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_IMMUNIZATIONITEMS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_IMMUNIZATIONITEMS):
    case SUCCESS(ACTION_TYPES.UPDATE_IMMUNIZATIONITEMS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_IMMUNIZATIONITEMS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/immunization-items';

// Actions

export const getEntities: ICrudGetAllAction<IImmunizationItems> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_IMMUNIZATIONITEMS_LIST,
  payload: axios.get<IImmunizationItems>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IImmunizationItems> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_IMMUNIZATIONITEMS,
    payload: axios.get<IImmunizationItems>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IImmunizationItems> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_IMMUNIZATIONITEMS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IImmunizationItems> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_IMMUNIZATIONITEMS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IImmunizationItems> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_IMMUNIZATIONITEMS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
